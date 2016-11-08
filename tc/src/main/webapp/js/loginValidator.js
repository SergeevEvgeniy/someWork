$(function () {
    $('#validate').click(function () {
        var formValid = true;
        $('input').each(function () {
            var formGroup = $(this).parents('.form-group');
            var glyphicon = formGroup.find('.form-control-feedback');
            if (($('#inputEmail').length == 0) && (($('#inputPassword').length == 0))) {
                formGroup.addClass('has-error').removeClass('has-success');
                glyphicon.addClass('glyphicon-remove').removeClass('glyphicon-ok');
                formValid = false;
            } else {
                formGroup.addClass('has-success').removeClass('has-error');
                glyphicon.addClass('glyphicon-ok').removeClass('glyphicon-remove');
            }
        });
    });
});

