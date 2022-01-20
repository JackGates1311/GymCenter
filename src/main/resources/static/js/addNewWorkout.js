$('select').selectpicker();

$("#newWorkoutPrice").on("keyup", function(){

    // alert("OKAY")

    var value = $(this).val();

    var numbersOnly = new RegExp('^[0-9]*$')

    if(numbersOnly.test(value) === false) {

        $(this).val(value.slice(0,-1));

    }

})

$("#newWorkoutLength").on("keyup", function(){

    // alert("OKAY")

    var value = $(this).val();

    var numbersOnly = new RegExp('^[0-9]*$')

    if(numbersOnly.test(value) === false) {

        $(this).val(value.slice(0,-1));

    }

})