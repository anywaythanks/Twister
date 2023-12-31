$(function () {
    let p = {
        speed: 20,
        duration: 4,
        startCallback: function () {
            $('.twist').attr('disabled', 'true');
            $('.stop').removeAttr('disabled');
        },
        slowDownCallback: function () {
            $('.stop').attr('disabled', 'true');
        },
        stopCallback: function ($stopElm) {
            $('.twist').attr('ok', 'true').html('<span>ok</span>').removeAttr('disabled');
            $('.stop').attr('disabled', 'true');
        }
    }
    let rouletter = $('div.case-line');
    rouletter.roulette(p);
    $('.stop').click(function () {
        rouletter.roulette('stop');
    });

    $('.twist').click(function () {
        // console.log("click");
        if (!$('.twist').attr("ok")) {
            const case_name = $('.twist').attr('data-case-name');
            const account_number = $('.twist').attr('data-account-number');
            const inventory_name = $('.twist').attr('data-inventory-name');
            $.post(`/me/${account_number}/twist/${case_name}/${inventory_name}`, function (data) {
                    p['stopDivNumber'] = Number($(`#${data.wonSlotName}`).attr("data-index"));
                    // console.log(data);
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
        max: 100,
        value: 70,
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
        value: 7,
        slide: function (event, ui) {
            updateDuration(ui.value);
            updateParamater();
        }
    });
    updateDuration($('#duration').slider('value'));
});