
let baseUrl = "";

let regAmount = /^[0-9.]{1,}$/;
let today = new Date().toISOString().slice(0, 10);
$('#txtCarTodayDate').val(today);

generateRentId();
generatePaymentId();



function getLastLoginUser() {
    $.ajax({
        url: "",
        method: "GET",
        success: function (res) {
            let login = res.data;
            getAllUserData(login.username, login.password);
        }
    })
}

function getAllUserData(username, password) {
    $.ajax({
        url: "" + username + "/" + password,
        method: "GET",
        success: function (res) {
            let customer = res.data;
            $('#txtCustId').val(customer.customerId);
        }
    })
}
//==================================================================================================================

$('#cmbType').change(function () {
    let type = $('#cmbType').find('option:selected').text();
    clearRentalFields();
    $('#cmbRegistrationNo').empty();
    $('#cmbRegistrationNo').append(new Option("-Select Registration No-", ""));
    $.ajax({
        url: baseUrl + "api/v1/car/getRegNo/" + type,
        method: "GET",
        success: function (res) {
            let i = 0;
            console.log(res.data);
            for (let regNo of res.data) {
                $('#cmbRegistrationNo').append(new Option(regNo, i));
                i++;
            }
        }
    })
});

$('#cmbRegistrationNo').change(function () {
    let registrationNo = $('#cmbRegistrationNo').find('option:selected').text();
    $.ajax({
        url: baseUrl + "api/v1/car/" + registrationNo,
        method: "GET",
        success: function (res) {
            let car = res.data;
            console.log(car);
            setCarDataToBookingFields(car);
        },
        error: function (ob) {
            clearRentalFields();
        }
    })
});

function setCarDataToBookingFields(car) {
    $('#divCarFrontView').empty();
    $('#divCarBackView').empty();
    $('#divCarSideView').empty();
    $('#divCarInteriorView').empty();

    $('#txtCarBrand').val(car.brand);
    $('#txtCarColor').val(car.color);
    $('#txtCarFuel').val(car.fuelType);
    $('#txtCarTransmission').val(car.transmissionType);
    $('#txtCarNoOfPassengers').val(car.noOfPassengers);
    $('#txtCarDailyRate').val(car.dailyRate);
    $('#txtCarMonthlyRate').val(car.monthlyRate);
    $('#txtCarFreeKmForPrice').val(car.freeKmForPrice);
    $('#txtCarFreeKmForDuration').val(car.freeKmForDuration);
    $('#txtCarLossDamageWavier').val(car.lossDamageWaiver);
    $('#txtCarPriceForExtraKm').val(car.priceForExtraKm);
    $('#txtCarCompleteKm').val(car.completeKm);

    let frontViewPath = car.frontViewImg;
    console.log(frontViewPath);
    let frontViewImg = frontViewPath.split("/media/kaleesha/Working_Space/Car_Rental_System_FinalExam/Front_End/Saved_Images/Cars")[1];
    let FrontViewImgSrc = "Saved_Images/Cars" + frontViewImg;

    let backViewPath = car.backViewImg;
    let backViewImg = backViewPath.split("/media/kaleesha/Working_Space/Car_Rental_System_FinalExam/Front_End/Saved_Images/Cars")[1];
    let backViewImgSrc = "Saved_Images/Cars" + backViewImg;
    console.log(backViewImgSrc);

    let sideViewPath = car.sideViewImg;
    let sideViewImg = sideViewPath.split("/media/kaleesha/Working_Space/Car_Rental_System_FinalExam/Front_End/Saved_Images/Cars")[1];
    let sideViewImgSrc = "Saved_Images/Cars" + sideViewImg;

    let interiorViewPath = car.internalViewImg;
    let interiorViewImg = interiorViewPath.split("/media/kaleesha/Working_Space/Car_Rental_System_FinalExam/Front_End/Saved_Images/Cars")[1];
    let interiorViewImgSrc = "Saved_Images/Cars" + interiorViewImg;

    let fvImg = `<img src=${FrontViewImgSrc} alt="NIC Front" style="background-size: cover;width: 100%;height: 100%">`;
    $('#divCarFrontView').append(fvImg);

    let bvImg = `<img src=${backViewImgSrc} alt="NIC Front" style="background-size: cover;width: 100%;height: 100%">`;
    $('#divCarBackView').append(bvImg);

    let svImg = `<img src=${sideViewImgSrc} alt="NIC Front" style="background-size: cover;width: 100%;height: 100%">`;
    $('#divCarSideView').append(svImg);

    let ivImg = `<img src=${interiorViewImgSrc} alt="NIC Front" style="background-size: cover;width: 100%;height: 100%">`;
    $('#divCarInteriorView').append(ivImg);
}

function clearRentalFields() {
    $('#divCarFrontView').empty();
    $('#divCarBackView').empty();
    $('#divCarSideView').empty();
    $('#divCarInteriorView').empty();

    $('#txtCarBrand').val("");
    $('#txtCarColor').val("");
    $('#txtCarFuel').val("");
    $('#txtCarTransmission').val("");
    $('#txtCarNoOfPassengers').val("");
    $('#txtCarDailyRate').val("");
    $('#txtCarMonthlyRate').val("");
    $('#txtCarFreeKmForPrice').val("");
    $('#txtCarFreeKmForDuration').val("");
    $('#txtCarLossDamageWavier').val("");
    $('#txtCarPriceForExtraKm').val("");
    $('#txtCarCompleteKm').val("");
}

function generateRentId() {
    $.ajax({
        url: "http://localhost:8080/Back_End_war/api/v1/CarRent/generateRentId",
        method: "GET",
        success: function (res) {
            $('#txtCarRentId').val(res.data);
        }
    })
}
function generatePaymentId() {
    $.ajax({
        url: "http://localhost:8080/Back_End_war/api/v1/payment/generatePaymentId",
        method: "GET",
        success: function (res) {
            $('#txtPaymentId').val(res.data);
        }
    })
}

$('#needDriver').click(function () {
    if ($(this).is(":checked")) {
        searchRandomDriverForRent();
    } else {
        clearRentalDriverFields();
    }
})

function searchRandomDriverForRent() {
    $.ajax({
        url: baseUrl + "api/v1/driver/getRandomDriver",
        method: "GET",
        success: function (res) {
            for (let driver of res.data) {
                $('#txtDriverLicenceNo').val(driver.licenceNo);
                $('#txtDriverName').val(driver.name);
                $('#txtDriverAddress').val(driver.address);
                $('#txtDriverContactNo').val(driver.contactNo);
                $('#txtDriverNIC').val(driver.nicNo);
            }
        },
        error: function (ob) {
            /*swal({
                title: "Error!",
                text: "Drivers are not available in this time.Please try again shortly",
                icon: "error",
                button: "Close",
                timer: 2000
            });*/
            alert("Error..!")
        }
    })
}

function clearRentalDriverFields() {
    $('#txtDriverLicenceNo').val("");
    $('#txtDriverName').val("");
    $('#txtDriverAddress').val("");
    $('#txtDriverContactNo').val("");
    $('#txtDriverNIC').val("");
}

$('#txtPaymentAmount').on('keyup', function (event) {
    checkAdvancedAmount();
});

function checkAdvancedAmount()  {
    let amount = $('#txtPaymentAmount').val();
    if (regAmount.test(amount)) {
        $('#txtPaymentAmount').css('border', '2px solid green');
        return true;
    } else {
        $('#txtPaymentAmount').css('border', '2px solid red');
        return false;
    }
}

$('#sendRequest').click(function () {
    let regNo = $('#cmbRegistrationNo').find('option:selected').text();
    if (regNo !== "" && regNo !== "-Select Registration No-" && $('#txtCarPickupDate').val()!=="" && $('#txtCarReturnDate').val()!="") {
        let custId = $('#txtCustId').val();
        searchCustomerById(custId);
    } else {
        alert("Please fill rental fields");
    }
});

function searchCustomerById(customerId) {
    $.ajax({
        url: baseUrl + "api/v1/customer/" + customerId,
        method: "GET",
        success: function (res) {
            let customer = res.data;
            console.log(res.data);
            searchCarByRegNo(customer);
        }
    });
}

function searchCarByRegNo(customer) {
    let registrationNo = $('#cmbRegistrationNo').find('option:selected').text();
    $.ajax({
        url: baseUrl + "api/v1/car/" + registrationNo,
        method: "GET",
        success: function (res) {
            let car = res.data;
            console.log("Success"+car.toString());
            searchDriverByLicenceNo(customer, car);
        }
    })
}

function searchDriverByLicenceNo(customer, car) {
    let licenceNo = $('#txtDriverLicenceNo').val();
    if ($('#txtDriverLicenceNo').val() === "") {
        licenceNo = null;
    }
    if (licenceNo != null) {
        $.ajax({
            url: baseUrl + "api/v1/driver/" + licenceNo,
            method: "GET",
            success: function (res) {
                let driver = res.data;
                console.log("Success"+driver.toString());
                addCarRent(customer, car, driver);
            }
        })
    } else {
        addCarRent(customer, car, null);
    }
}

function addCarRent(customer, car, driver) {

    let rentId = $('#txtCarRentId').val();
    console.log("rentId : "+rentId);
    let today = $('#txtCarTodayDate').val();
    let pickupDate = $('#txtCarPickupDate').val();
    let returnDate = $('#txtCarReturnDate').val();
    let status = "Pending";
    var carRent = {
        rentId: rentId,
        date: today,
        pickUpDate: pickupDate,
        returnDate: returnDate,
        status: status,
        customer: customer,
        car: car,
        driver: driver
    }


    $.ajax({
        url: baseUrl + "api/v1/CarRent",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(carRent),

        success: function (res) {
            getLastRent(rentId, customer);
            alert("Success");
        },
        error: function (ob) {
            alert("Error");
        }
    })
}

function getLastRent(rentId, customer) {
    $.ajax({
        url: baseUrl + "api/v1/CarRent/" + rentId,
        method: "GET",
        success: function (res) {
            let carRent = res.data;
            addAdvancedPayment(carRent, customer);
        }
    })
}

function addAdvancedPayment(carRent, customer) {
    let paymentId = $('#txtPaymentId').val();
    let today = $('#txtCarTodayDate').val();
    let amount = $('#txtPaymentAmount').val();
    if ($('#txtPaymentAmount').val() === "") {
        amount = 0.0;
    }
    var payment = {
        paymentId: paymentId,
        date: today,
        amount: amount,
        rental: carRent,
        customer: customer
    }

    $.ajax({
        url: baseUrl + "api/v1/payment",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(payment),
        success: function (res) {
            console.log("Payment Success");
            clearCarRentFields();
            generateRentId();
            generatePaymentId();
        }
    })
}

function clearCarRentFields() {
    // $('#cmbType').find('option:selected').text("- Select Car Type -");
    $('#cmbRegistrationNo').find('option:selected').text("");
    $('#txtCarBrand').val("");
    $('#txtCarColor').val("");
    $('#txtCarFuel').val("");
    $('#txtCarTransmission').val("");
    $('#txtCarNoOfPassengers').val("");
    $('#txtCarDailyRate').val("");
    $('#txtCarMonthlyRate').val("");
    $('#txtCarFreeKmForPrice').val("");
    $('#txtCarFreeKmForDuration').val("");
    $('#txtCarLossDamageWavier').val("");
    $('#txtCarPriceForExtraKm').val("");
    $('#txtCarCompleteKm').val("");
    $('#divCarFrontView').empty();
    $('#divCarBackView').empty();
    $('#divCarSideView').empty();
    $('#divCarInteriorView').empty();
    $('#txtCarPickupDate').val("");
    $('#txtCarReturnDate').val("");
    $('#needDriver').prop('checked', false);
    $('#txtDriverLicenceNo').val("");
    $('#txtDriverName').val("");
    $('#txtDriverAddress').val("");
    $('#txtDriverContactNo').val("");
    $('#txtDriverNIC').val("");
    $('#txtPaymentAmount').val("");
    $('#txtPaymentAmount').css('border', '1px solid #ced4da');
}

$('#btnCancleRental').click(function () {
    clearCarRentFields();
    generateRentId();
    generatePaymentId();
})