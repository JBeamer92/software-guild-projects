$(document).ready(function() {
    
    $("#add-org-button").click(event, function(){
        displayAddOrgForm();
    });
    
    $("#add-org-cancel").click(event, function(){
        clearForm();
        $("#add-org-div").hide();
        $("#org-list-div").show();
    });
    
});

function toggleDescription(orgId) {
    $("#desc-"+orgId).toggle();
}

function displayAddOrgForm() {
    $("#org-list-div").hide();
    $("#add-org-div").show();
}

function clearForm() {
    $("#orgName").val("");
    $("#orgDesc").val("");
    $("#orgLocation").val("");
    $("#orgPhoneType").val("");
    $("#orgPhoneNum").val("");
    $("#orgHeroes").val("");
}