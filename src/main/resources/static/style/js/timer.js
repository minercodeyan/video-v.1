
window.onload = function () {
    window.setInterval(function () {
        var now = Date.parse(new Date())
        if (now < end) {
            var timelefT = end - now;
            var days = Math.floor(timelefT / (1000 * 60 * 60 * 24));
            if (days < 10) days = "0" + days;
            var hours = Math.floor((timelefT % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            if (hours < 10) hours = "0" + hours;
            var minutes = Math.floor((timelefT % (1000 * 60 * 60)) / (1000 * 60));
            if (minutes < 10) minutes = "0" + minutes;
            var seconds = Math.floor((timelefT % (1000 * 60)) / 1000);
            if (seconds < 10) seconds = "0" + seconds;
            document.getElementById("countdown").innerHTML = days + "дней " + hours + "часов " + minutes + "минут " + seconds + "секунд";
        } else return;
    }, 1000);
};
