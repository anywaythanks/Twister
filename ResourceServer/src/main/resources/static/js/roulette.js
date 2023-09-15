(function ($) {
    let Roulette = function (options) {
        let defaultSettings = {
            maxPlayCount: null, // x >= 0 or null
            speed: 10, // x > 0
            stopDivNumber: null, // x >= 0 or null or -1
            rollCount: 3, // x >= 0
            duration: 3, //(x second)
            stopCallback: function () {
            },
            startCallback: function () {
            },
            slowDownCallback: function () {
            }
        }
        let defaultProperty = {
            playCount: 0,
            $rouletteTarget: null,
            divCount: null,
            $dives: null,
            originalStopDivNumber: null,
            totalHeight: null,
            topPosition: 0,

            maxDistance: null,
            slowDownStartDistance: null,

            isRunUp: true,
            isSlowdown: false,
            isStop: false,

            distance: 0,
            runUpDistance: null,
            slowdownTimer: null,
            isIE: navigator.userAgent.toLowerCase().indexOf('msie') > -1 // TODO IE
        };
        let p = $.extend({}, defaultSettings, options, defaultProperty);

        let reset = function () {
            p.maxDistance = defaultProperty.maxDistance;
            p.slowDownStartDistance = defaultProperty.slowDownStartDistance;
            p.distance = defaultProperty.distance;
            p.isRunUp = defaultProperty.isRunUp;
            p.isSlowdown = defaultProperty.isSlowdown;
            p.isStop = defaultProperty.isStop;
            p.topPosition = defaultProperty.topPosition;

            clearTimeout(p.slowDownTimer);
        }

        let slowDownSetup = function () {
            if (p.isSlowdown) {
                return;
            }
            p.slowDownCallback();
            p.isSlowdown = true;
            p.slowDownStartDistance = p.distance;
            p.maxDistance = p.distance + (2 * p.totalHeight);
            p.maxDistance += p.divHeight - p.topPosition % p.divHeight;
            if (p.stopDivNumber != null) {
                p.maxDistance += (p.totalHeight - (p.maxDistance % p.totalHeight) + (p.stopDivNumber * p.divHeight))
                    % p.totalHeight;
            }
        }

        let roll = function () {
            let speed_ = p.speed;

            if (p.isRunUp) {
                if (p.distance <= p.runUpDistance) {
                    let rate_ = ~~((p.distance / p.runUpDistance) * p.speed);
                    speed_ = rate_ + 1;
                } else {
                    p.isRunUp = false;
                }

            } else if (p.isSlowdown) {
                let rate_ = ~~(((p.maxDistance - p.distance) / (p.maxDistance - p.slowDownStartDistance)) * (p.speed));
                speed_ = rate_ + 1;
            }

            if (p.maxDistance && p.distance >= p.maxDistance) {
                p.isStop = true;
                reset();
                p.stopCallback(p.$rouletteTarget.find('div').eq(p.stopDivNumber));
                return;
            }
            p.distance += speed_;
            p.topPosition += speed_;
            if (p.topPosition >= p.totalHeight) {
                p.topPosition = p.topPosition - p.totalHeight;
            }
            // TODO IE
            if (p.isIE) {
                p.$rouletteTarget.css('top', '-' + p.topPosition + 'px');
            } else {
                // TODO more smooth roll
                p.$rouletteTarget.css('transform', 'translate(0px, -' + p.topPosition + 'px)');
            }
            setTimeout(roll, 1);
        }

        let init = function ($roulette) {
            defaultProperty.originalStopDivNumber = p.stopDivNumber;
            if (!p.dives) {
                p.$dives = $roulette.contents('div');
                p.divCount = p.$dives.length;
                p.divHeight = p.$dives.eq(0).height();
                // $roulette.css({'height': (p.divHeight + 'px')});
                p.totalHeight = p.divCount * p.divHeight;
                p.runUpDistance = 2 * p.divHeight;
                $roulette.contents('div').remove();
            }
            p.$rouletteTarget = $('<div>').css({
                'position': 'relative',
                'top': '0'
            }).attr('class', "roulette-inner");
            $roulette.append(p.$rouletteTarget);
            p.$rouletteTarget.append(p.$dives);
            p.$rouletteTarget.append(p.$dives.eq(0).clone().removeAttr('id'));
            $roulette.show();
        }

        let start = function () {
            p.playCount++;
            if (p.maxPlayCount && p.playCount > p.maxPlayCount) {
                return;
            }
            p.stopDivNumber = $.isNumeric(defaultProperty.originalStopDivNumber) && Number(defaultProperty.originalStopDivNumber) >= 0 ?
                Number(defaultProperty.originalStopDivNumber) : Math.floor(Math.random() * p.divCount);
            p.startCallback();
            roll();
            p.slowDownTimer = setTimeout(function () {
                slowDownSetup();
            }, p.duration * 1000);
        }

        let stop = function (option) {
            if (!p.isSlowdown) {
                if (option) {
                    let stopDivNumber = Number(option.stopDivNumber);
                    if (0 <= stopDivNumber && stopDivNumber <= (p.divCount - 1)) {
                        p.stopDivNumber = option.stopDivNumber;
                    }
                }
                slowDownSetup();
            }
        }
        var option = function (options) {
            p = $.extend(p, options);
            p.speed = Number(p.speed);
            p.duration = Number(p.duration);
            p.duration = p.duration > 1 ? p.duration - 1 : 1;
            defaultProperty.originalStopDivNumber = options.stopDivNumber;
        }

        let ret = {
            start: start,
            stop: stop,
            init: init,
            option: option
        }
        return ret;
    }

    let pluginName = 'roulette';
    $.fn[pluginName] = function (method, options) {
        return this.each(function () {
            let self = $(this);
            let roulette = self.data('plugin_' + pluginName);

            if (roulette) {
                if (roulette[method]) {
                    roulette[method](options);
                } else {
                    console && console.error('Method ' + method + ' does not exist on jQuery.roulette');
                }
            } else {
                roulette = new Roulette(method);
                roulette.init(self, method);
                $(this).data('plugin_' + pluginName, roulette);
            }
        });
    }
})(jQuery);
