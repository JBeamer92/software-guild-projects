$(document).ready(function() {
    
    $("#add-sight-button").click(event, function(){
        displayAddSightForm();
    });
    
    $("#add-power-cancel").click(event, function(){
        clearForm();
    });
    
    $("#edit-power-cancel").click(event, function(){
        $("#edit-power-form").hide();
        $("#add-power-form").show();
    });
    
});

var powerSelected;

function displayEditForm(powerId) {
    
    $.ajax({
        method: "GET",
        url: "http://localhost:8081/SuperHeroSightings/getPower?powerId="+powerId,
        success: function(power) {
            powerSelected = powerId;
            $("#editPowerName").val(power.name);
            $("#editPowerDesc").val(power.description);
            $("#editPowerId").val(power.id);
            $("#add-power-form").hide();
            $("#edit-power-form").show();
        },
        error: function() {
            alert("Unable to edit Power.");
        }
    });
    
    
}

function clearForm() {
    $("#powerName").val("");
    $("#powerDesc").val("");
}