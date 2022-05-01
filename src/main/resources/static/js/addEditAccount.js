$("#dayOfUserDateBirth").on("keyup", function(){

    let value = $(this).val();

    let numbersOnly = new RegExp('^[0-9]*$');

    if(numbersOnly.test(value) === false) {

        $(this).val(value.slice(0,-1));

    }

});

$("#yearOfUserDateBirth").on("keyup", function(){

    let value = $(this).val();

    let numbersOnly = new RegExp('^[0-9]*$');

    if(numbersOnly.test(value) === false) {

        $(this).val(value.slice(0,-1));

    }

});

$("#userPhoneNumber").on("keyup", function(){

    let value = $(this).val();

    let mobilePhoneNumberOnly = new RegExp('^[0-9+]*$');

    if(mobilePhoneNumberOnly.test(value) === false || (value.charAt(value.length - 1) === '+' && value.length > 1)) {

        $(this).val(value.slice(0,-1));

    }
});

$("#cancelAddEditAccount").on("click", function() {

    $(location).attr('href', '/workouts');

});

$("#btnSetNewPassword").on("click", function(e) {

        let userNewPassword = $("#userNewPassword").val();
        let userNawPasswordRepeated = $("#userNewPasswordRepeated").val();

        if(userNewPassword === "" && userNawPasswordRepeated === "") {

            alert("Passwords are not entered");

            e.preventDefault();

        }

        if (userNewPassword !== userNawPasswordRepeated) {

            alert("Entered passwords are not matching to each other");

            e.preventDefault();

        }

});
