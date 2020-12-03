// pagination template
function template(data) {
    var grid = '';
    console.log(data);
    var isAdmin = $('#role').html();
    var button;
    var formatter = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'});

    if (data.length == 0) {
        grid = "<div class='column is-full subtitle content has-text-centered'>No records found</div>";
    } else {
        $.each(data, function (index, cake) {
            if (isAdmin === 'admin') {
                button = `<a href="viewCake?idCake=${cake.idCake}" class="fa fa-pencil" style="color: black"></a>`
            } else {
                button = `<button href="shoppingCart?action=add&idCake=${cake.idCake}" class="button fa fa-shopping-cart is-white add-to-cart"></button>`
            }

            grid += `<div class="column is-4">
                    <div class="tile is-parent box">
                        <div class="tile is-4 image is-child">
                            <img style="object-fit: fill" src="${cake.imageCake}">
                        </div>
                        <div class="tile is-child pl-5">
                            <p class="title">${cake.nameCake}</p>
                            <p class="subtitle">${cake.idCategory}</p>
                            <p>${cake.descCake}</p>
                            <p><b>Price:</b> ${formatter.format(cake.price)}</p>
                            <p><b>MGF:</b> ${cake.createDate} <b>EXP:</b> ${cake.expirationDate}</p>
                            <p></p>
                        </div>
                        ${button}
                    </div>
            </div>`;
        });
    }
    return grid;
}

var dataContainer = $('#theGrid');

function invokePagination(source) {
    dataContainer.pagination({
        dataSource: function (done) {
            $.ajax({
                type: 'GET',
                url: source,
                success: function (response) {
                    done(response);
                }
            })
        },
        pageSize: 9,
        className: 'custom-paginationjs',
        showNext: false,
        showPrevious: false,
        ajax: {
            beforeSend: function () {
                $('#grid-container').html("<div class='column is-full subtitle content has-text-centered'>Loading data...</div>");
            }
        },
        callback: function (data, pagination) {
            console.log(data);
            $('#grid-container').html(template(data));
            // ajax to add to cart button
            $('.add-to-cart').click(function () {
                var href = $(this).attr('href');
                $.ajax({
                    type: 'POST',
                    url: href,
                    success: function() {
                        alert("Added!");
                    }
                })
            })
        }
    });
}

invokePagination('viewAllCakes');

// search with style (just to show)
$('.dropdown')
        .mouseover(function () {
            $('#dropdown-menu').css('display', 'block');
        })
        .mouseout(function () {
            $('#dropdown-menu').css('display', 'none');
        });

$('.dropdown-item').click(function () {
    $('#display-dropdown').html($(this).html());
});

$('#search-by-name').click(function () {
    $('#search-field').html(`<input type="hidden" name="searchBy" value="name"/>
                    <input class="input" name="txtSearch" type="text" placeholder="Find a cake" required>`);
});

$('#search-by-price').click(function () {
    $('#search-field').html(`<input type="hidden" name="searchBy" value="price"/>
                    <input name="from" class="input is-2" type="number" min="0" required placeholder="From" style="width: 49%">
                    <input name="to" class="input is-2" type="number" min="0" required placeholder="To" style="width: 49%">`);
});

// get categories at first
var options = "";
$.ajax({
    url: 'category',
    success: function (result) {
        $.each(result, function (index, category) {
            console.log(result);
            options += `<option value="${category.idCategory}">${category.nameCate}</option>`;
        });
        console.log(options);
    },
});

// later just render on the view
$('#search-by-category').click(function () {
    $('#search-field').html(`
            <input type="hidden" name="searchBy" value="category"/>
            <span class="select">
                <select class="dropdown button" name="idCategory" id="categories"> 
                    ${options}
                </select>
            </span>`)
})

// the actual searching (used to send data to server)
$('#search-field').submit(function (event) {
    // prevent form submission to reload page
    event.preventDefault();

    // instead use ajax to search whilst paging
    invokePagination("searchCake?" + $('#search-field').serialize());
})

