$("#workoutPriceMin").on("keyup", function(){

    let value = $(this).val();

    let numbersOnly = new RegExp('^[0-9]*$');

    if(numbersOnly.test(value) === false) {

        $(this).val(value.slice(0,-1));

    }
})

$("#workoutPriceMax").on("keyup", function(){

    let value = $(this).val();

    let numbersOnly = new RegExp('^[0-9]*$');

    if(numbersOnly.test(value) === false)
        $(this).val(value.slice(0,-1));
})