window.addEventListener("scroll", function(){
    let block = document.getElementById('infinite-scroll');
    let counter = 1;
    let contentHeight = block.offsetHeight;      // 1) высота блока контента вместе с границами
    let yOffset       = window.pageYOffset;      // 2) текущее положение скролбара
    let window_height = window.innerHeight;      // 3) высота внутренней области окна документа
    let y = yOffset + window_height;
    if(y >= contentHeight)
{

}
});
