$(document).ready(function () {
    $.ajax({
        url: 'category',
        success: function (result) {
            var options = "";
            console.log(result[0]);
            $.each(result, function (index, category) {
                console.log($('#idCategory').html());
                var selected = "";
                var categoryToCheck = $('#idCategory').html() + "";
                if(categoryToCheck === category.nameCate)
                    selected = "selected";
                options += `<option value="${category.idCategory}" ${selected}>${category.nameCate}</option>`
            });
            console.log(options);
            $('select[name=idCategory').html(options);
        }
    })
});

jQuery.validator.addMethod('valid_idCake', function (value) {
    return value.trim().match('Cake-[0-9]{3}');
});

jQuery.validator.addMethod('valid_expireDate', function (value, element, param) {
    var expireDate = new Date(value);
    var createDate = new Date($(param).val());

    return expireDate > createDate;
})

$('#add-cake-form').validate({
    rules: {
        'idCake': {
            valid_idCake: true
        },
        'expirationDate': {
            valid_expireDate: 'input[name=createDate]'
        }
    },
    messages: {
        'idCake': {
            valid_idCake: 'Id Cake must assemble Cake-xxx (0-9)'
        },
        'expirationDate': {
            valid_expireDate: 'Expired Date must be after Create Date'
        }
    },
    errorClass: 'help is-danger'
})

// change label when a file is chosen
$('input[type="file"]').change(function (e) {
    var fileName = e.target.files[0].name;
    $('.file-name').html(fileName);
});

