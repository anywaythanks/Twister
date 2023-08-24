$(function () {
    let p = {
        startCallback: function () {
            $('.twist').attr('disabled', 'true');
            $('.stop').removeAttr('disabled');
        },
        slowDownCallback: function () {
            $('.stop').attr('disabled', 'true');
        },
        stopCallback: function ($stopElm) {
            $('.twist').attr('ok', 'true').html('ok').removeAttr('disabled');
            $('.stop').attr('disabled', 'true');
        }
    }
    let rouletter = $('div.case-line');
    rouletter.roulette(p);
    $('.stop').click(function () {
        rouletter.roulette('stop');
    });

    $('.twist').click(function () {
        if (!$('.twist').attr("ok")) {
            const case_name = $('.twist').attr('data-case-name');
            const account_number = $('.twist').attr('data-account-number');
            $.post(`/me/${account_number}/twist/${case_name}`, function (data) {
                    p['stopDivNumber'] = Number($(`#${data.caseName}`).attr("data-index"));
                    updateParamater();
                    rouletter.roulette('start');
                }
            );
        } else {
            location.reload();
        }
    });

    let updateParamater = function () {
        rouletter.roulette('option', p);
    }
    let updateSpeed = function (speed) {
        $('.speed_param').text(speed);
    }
    $('#speed').slider({
        min: 1,
        max: 30,
        value: 10,
        slide: function (event, ui) {
            updateSpeed(ui.value);
            updateParamater();
        }
    });
    updateSpeed($('#speed').slider('value'));

    let updateDuration = function (duration) {
        $('.duration_param').text(duration);
    }
    $('#duration').slider({
        min: 2,
        max: 10,
        value: 3,
        slide: function (event, ui) {
            updateDuration(ui.value);
            updateParamater();
        }
    });
    updateDuration($('#duration').slider('value'));
});