$(document).ready(function () {
    function getDate() {
        var date = new Date();
        var hour = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
        $('#clock').text(hour + ':' + minutes + ':' + seconds);
        setTimeout(getDate, 1000);
    }
    getDate();
});
