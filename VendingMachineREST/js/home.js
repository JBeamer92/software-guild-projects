$(document).ready(function() {
    loadItems();

    // add change
    $('#add-dollar-btn').click(event, function() {
        addAmountToTotal(1.00);
    });
    $('#add-quarter-btn').click(event, function() {
        addAmountToTotal(0.25);
    });
    $('#add-dime-btn').click(event, function() {
        addAmountToTotal(0.10);
    });
    $('#add-nickel-btn').click(event, function() {
        addAmountToTotal(0.05);
    });

    // make purchase
    $('#make-purchase-btn').click(event, function() {
        clearMessages();
        clearChange();
        makePurchase();
    });

    // change return
    $('#change-return-btn').click(event, function() {
        changeReturn();
    });
});

function loadItems() {

    $('#divLeft').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function(itemArray) {
            $.each(itemArray, function(index, item) {

                var id = item.id;
                var name = item.name;
                var price = item.price;
                var quantity = item.quantity;

                var insert = "<div id='item-div-" + id + "' class='col-md-3 col-md-offset-1 text-center item-div' onclick='selectItem(" + id + ")'>";
                insert += "<p id='item" + id + "-id' class='text-left'>" + id + "</p>";
                insert += "<p id='item" + id + "-name'>" + name + "</p>";
                insert += "<p>$<span id='item" + id + "-price'>" + price.toFixed(2) + "</span></p>";
                insert += "<p>Quantity Left: <span id='item" + id + "-quantity'>" + quantity + "</span></p>";
                insert += "</div>";

                $("#divLeft").append(insert);
            });
        },
        error: function() {
            $('#message-box').val('Vending machine out of order!');
        }
    });
}

function selectItem(itemNum) {
    clearMessages();
    clearChange();
    $('#item-num-box').val(itemNum);
}

function makePurchase() {
    if ($('#item-num-box').val()) {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/money/' + $('#total-box').val() + '/item/' + $('#item-num-box').val(),
            success: function(data) {
                $('#message-box').val('Thank you!');
                var response = '';
                if (data.quarters > 0) {
                    response += data.quarters + ' Quarters ';
                }
                if (data.dimes > 0) {
                    response += data.dimes + ' Dimes ';
                }
                if (data.nickels > 0) {
                    response += data.nickels + ' Nickels ';
                }
                $('#change-box').val(response);
                $('#total-box').val('0.00');
                loadItems();
            },
            error: function(data) {
                var json = $.parseJSON(data.responseText);
                $('#message-box').val(json.message);
            }
        });
    } else {
        $('#message-box').val('Please choose an item!');
    }
}

function addAmountToTotal(amount) {
    clearMessages();
    clearChange();
    var total = parseFloat($('#total-box').val());
    total += amount;
    $('#total-box').val(total.toFixed(2));
}

function changeReturn() {
    var total = parseFloat($('#total-box').val());
    total.toFixed(2);
    var quarters = 0;
    var dimes = 0;
    var nickels = 0;

    while (total - 0.25 >= 0) {
        quarters++;
        total = (total - 0.25).toFixed(2);
    }
    while (total - 0.10 >= 0) {
        dimes++;
        total = (total - 0.10).toFixed(2);
    }
    while (total - 0.05 >= 0) {
        nickels++;
        total = (total - 0.05).toFixed(2);
    }

    var response = '';
    if (quarters > 0) {
        response += quarters + ' Quarters ';
    }
    if (dimes > 0) {
        response += dimes + ' Dimes ';
    }
    if (nickels > 0) {
        response += nickels + ' Nickels ';
    }
    $('#change-box').val(response);
    $('#total-box').val('0.00');
}

function clearMessages() {
    $('#message-box').val('');
}

function clearChange() {
    $('#change-box').val('');
}
