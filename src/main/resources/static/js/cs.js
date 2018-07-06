$(function () {
    $("[data-action]").each(function () {
       $(this).on('click', function () {
           var href = $(this).attr("href");
           $(".modal-body").text('您是否要' + $(this).data('action') + '？');
           $(".btn-primary").click(function(){
                window.location.href=href;
           })
        });
    })
});

