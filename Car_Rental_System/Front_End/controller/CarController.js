var baseUrl4="http://localhost:8080/BackEnd_war/api/v1/car";

$("#addCar").click(function (){
    addCar();
});

function addCar() {
    var carID = $('#carRegisterId').val();
    var carBrand = $('#carBrand').val();
    var carType = $('#carType').val();
    var carPassengers = $('#carPassengers').val();
    var carTransmissionType = $('#carTransmissionType').val();
    var carFuelType = $('#fuelType').val();
    var carColor = $('#carColor').val();
    var carDailyRate = $('#dailyRate').val();
    var carMotnhlyRate = $('#monthlyRate').val();
    var carMileagePrice = $('#mileagePrice').val();
    var carMileageDuration = $('#mileageDuration').val();
    var carLossDamageWaiver = $('#lossDamageWaiver').val();
    var carPriceOfExtraKM = $('#priceOfExtraKM').val();
    var carCompleteKm = $('#completeKm').val();

    var car = {
        registrationNO: carID,
        brand: carBrand,
        type: carType,
        noOfPassengers: carPassengers,
        transmissionType: carTransmissionType,
        fuelType: carFuelType,
        color: carColor,
        dailyRate: carDailyRate,
        monthlyRate: carMotnhlyRate,
        freeKmForPrice: carMileagePrice,
        freeKmForDuration: carMileageDuration,
        lossDamageWaiver: carLossDamageWaiver,
        priceForExtraKm: carPriceOfExtraKM,
        completeKm: carCompleteKm,
    }
    console.log(car);
    $.ajax({
        url: baseUrl4,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(car),
        success: function (resp) {
            uploadCarImages(carID);
            /*if (resp.data() === true) {
                clearSignupTextFields();
            }
            */
            alert("Car Add Successfully");
        },
        error: function (ob) {

            alert("Unsuccessfully");
        }
    });
}

function uploadCarImages(id) {
    var fileObjectCar1 = $('#frontViewIm')[0].files[0];
    var fileNameCar1 = id + "-carfront-" + $('#frontViewIm')[0].files[0].name;

    var fileObjectCar2 = $('#backViewIm')[0].files[0];
    var fileNameCar2 = id + "-carback-" + $('#backViewIm')[0].files[0].name;

    var fileObjectCar3 = $('#internalViewIm')[0].files[0];
    var fileNameCar3 = id + "-carInterImg-" + $('#internalViewIm')[0].files[0].name;

    var fileObjectCar4 = $('#sideViewIm')[0].files[0];
    var fileNameCar4 = id + "-carSideImg-" + $('#sideViewIm')[0].files[0].name;

    var data = new FormData();
    data.append("frontImg", fileObjectCar1, fileNameCar1);
    data.append("backImg", fileObjectCar2, fileNameCar2);
    data.append("interImg", fileObjectCar3, fileNameCar3);
    data.append("sideImg", fileObjectCar4, fileNameCar4);

    $.ajax({
        url: baseUrl4 + "/up/" + id,
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