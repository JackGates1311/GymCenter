$("#workoutPriceMin").on("keyup", function(){

    // alert("OKAY")

    var value = $(this).val();

    var numbersOnly = new RegExp('^[0-9]*$')

    if(numbersOnly.test(value) === false) {

        $(this).val(value.slice(0,-1));

    }

})



$("#workoutPriceMax").on("keyup", function(){

    // alert("OKAY")

    var value = $(this).val();

    var numbersOnly = new RegExp('^[0-9]*$')

    if(numbersOnly.test(value) === false)
        $(this).val(value.slice(0,-1));

})

/*$("#workoutName").on("keyup", function(){

    alert("OK");

    var value = $(this).val();

    $("table tr").each(function(records){

        if(records != 0) {

            var currentRow=$(this).closest("tr");
            var id = currentRow.find("td:eq(3)").text();

            alert(currentRow.find("td:eq(1)").text());

            if(id.indexOf(value) != 0) {

                $(this).hide();

            } else {

                $(this).show();
            }
        }

    })

});*/