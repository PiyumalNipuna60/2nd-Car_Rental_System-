
var baseUrl3="http://localhost:8080/BackEnd_war/api/v1/customer";
function saveCustomer() {

    var cusID=$('#cusID').val();
    var cusName=$('#cusName').val();
    var cusAddress=$('#cusAddress').val();
    var contact=$('#cusContact').val();
    var cusEmail=$('#cusEmail').val();
    var cusNicNo=$('#cusNic').val();
    var licenceNo=$('#cusLicence').val();
    var username=$('#cusUserName').val();
    var password=$('#password').val();

    var customer = {
        customerId: cusID,
        name: cusName,
        address: cusAddress,
        contactNo: contact,
        email: cusEmail,
        nicNo: cusNicNo,
        licenceNo:licenceNo,
        username: username,
        password: password
    }
//console.log(customer);
    $.ajax({
        url: baseUrl3,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(customer),
        success: function (resp) {
            uploadCustomerImages(cusID);
            /*if (resp.data() === true) {
                clearSignupTextFields();
            }
            */
            alert("Registration Successfully");
        },
        error: function (ob) {

          alert("unsuccessfully");
        }
    });

}
function clearSignupTextFields(){
    $("#cusName").val('');
    $("#cusAddress").val('');
    $("#cusContact").val('');
    $("#cusNic").val('');
    $("#cusUserName").val('');
    $("#password").val('');
    $("#cusEmail").val('');
    $("#imgNiCFront").val('');

}
function uploadCustomerImages(id) {
    var fileObjectNic1 = $('#imgNiCFront')[0].files[0];
    var fileNameNic1 = id + "-nicfront-" + $('#imgNiCFront')[0].files[0].name;

    var fileObjectNic2 = $('#imgNiCBack')[0].files[0];
    var fileNameNic2 = id + "-nicback-" + $('#imgNiCBack')[0].files[0].name;

    var fileObjectLicence = $('#imgLicence')[0].files[0];
    var fileNameLicence = id + "-licence-" + $('#imgLicence')[0].files[0].name;

    var data = new FormData();
    data.append("nicf", fileObjectNic1, fileNameNic1);
    data.append("nicb", fileObjectNic2, fileNameNic2);
    data.append("licenceImg", fileObjectLicence, fileNameLicence);

    $.ajax({
        url: baseUrl3 + "/up/" + id,
        method: "PUT",
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (res) {
            console.log("Uploaded");
            clearSignupTextFields();
        }
    })
}
generateCustomerId();
function generateCustomerId() {
    $.ajax({
        url: baseUrl3 + "/generateCustomerId",
        method: "GET",
        success: function (res) {
            $('#cusID').val(res.data);
        }
    })
}
