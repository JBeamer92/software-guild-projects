/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

    
    // add change
    $('#add-dollar-btn').click(event, function () {
        clearChange();
        addAmountToTotal(1.00);
    });
    $('#add-quarter-btn').click(event, function () {
        clearChange();
        addAmountToTotal(0.25);
    });
    $('#add-dime-btn').click(event, function () {
        clearChange();
        addAmountToTotal(0.10);
    });
    $('#add-nickel-btn').click(event, function () {
        clearChange();
        addAmountToTotal(0.05);
    });
    
    // change return
    $('#change-return-btn').click(event, function () {
        changeReturn();
    });
});


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
        response += nickels + ' Nickels';
    }
    $('#change-box').val(response);
    $('#total-box').val('0.00');
    clearItemSelection();
    clearMessages();
}

function selectItem(itemNum) {
    clearMessages();
    clearChange();
    $('#item-num-box').val(itemNum);
}

function clearMessages() {
    $('#message-box').val('');
}


function clearChange() {
    $('#change-box').val('');
}

function clearItemSelection() {
    $('#item-num-box').val('');
}