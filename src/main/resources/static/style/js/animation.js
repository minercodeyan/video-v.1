
$(function (){
console.log(2);
$(".nav-link").mouseenter(function () {
    $(this).addClass("text-secondary");
    $(this).addClass("underline");
    });
$(".nav-link").mouseout(function () {
    $(this).removeClass("text-secondary");
    $(this).removeClass("underline");
    });
});