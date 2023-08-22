function formatSeconds(distance) {
    let days = Math.floor(distance / (60 * 60 * 24));
    let hours = Math.floor((distance % (60 * 60 * 24)) / (60 * 60));
    let minutes = Math.floor((distance % (60 * 60)) / (60));
    let seconds = distance % 60;
    let days_s = days + "d ";
    let hours_s = hours + "h ";
    let minutes_s = minutes + "m ";
    let seconds_s = seconds + "s";
    if (hours < 10) {
        hours_s = "0" + hours_s;
    }
    if (minutes < 10) {
        minutes_s = "0" + minutes_s;
    }
    if (seconds < 10) {
        seconds_s = "0" + seconds_s;
    }
    if (days === 0) {
        days_s = "";
        if (hours === 0) {
            hours_s = "";
            if (minutes === 0) {
                minutes_s = "";
                if (seconds === 0) {
                    seconds_s = "";
                }
            }
        }
    }
    return days_s + hours_s + minutes_s + seconds_s;
}