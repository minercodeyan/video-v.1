$(function (){
        $(".anim").animate({
            opacity: 1,
        },1000,"linear",function (){

        })

        $(".nav-link").mouseenter(function () {
            jQuery(this).css('opacity', '0.2');
                $(this).addClass("underline");	// изменяем текстовое содержимое нашему блоку и указываем цвет текста
        });
        $(".nav-link").mouseleave(function () {
            jQuery(this).css('opacity', '1');

                $(this).removeClass("underline");
                // изменяем текстовое содержимое нашему блоку и указываем цвет текста
        });

});