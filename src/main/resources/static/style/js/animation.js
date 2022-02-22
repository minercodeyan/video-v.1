$(function (){
        $(".anim").animate({
            opacity: 1,
        },1000,"linear",function (){

        })

        $(".nav-link").mouseenter(function () {
            $(this).animate({
                opacity: 0.4, // прозрачность элемента
            }, 500,"linear", function () {
                $(this).addClass("underline");	// изменяем текстовое содержимое нашему блоку и указываем цвет текста
            });
        });
        $(".nav-link").mouseleave(function () {
            $(this).animate({
                opacity: 1, // прозрачность элемента
            }, 0, "linear", function () {
                $(this).removeClass("underline");
                // изменяем текстовое содержимое нашему блоку и указываем цвет текста
            });
        });

});