$(".sidebar-dropdown > a").click(function () {
    $(".sidebar-submenu").slideUp(200);
    if ($(this).parent().hasClass("active")) {
        $(".sidebar-dropdown").removeClass("active");
        $(this).parent().removeClass("active");
    } else {
        $(".sidebar-dropdown").removeClass("active");
        $(this).next(".sidebar-submenu").slideDown(200);
        $(this).parent().addClass("active");
    }
});

$("#close-sidebar").click(function () {
    $(".page-wrapper").removeClass("toggled");
});
$("#show-sidebar").click(function () {
    $(".page-wrapper").addClass("toggled");
});

$(function(){
    $(".input-date").datepicker({
       showOn : "button",
       dateFormat:'yy/mm/dd',
       buttonImage:'https://ps9103.s3.us-east-2.amazonaws.com/public/field_date+(1).png',
       buttonImageOnly : false,
       buttonText:'Date',
    });

});

$('.dropdown-toggle').dropdown()