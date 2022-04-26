// noinspection JSUnresolvedFunction

$('select').selectpicker();

$("#newWorkoutPrice").on("keyup", function(){

    let value = $(this).val();

    let numbersOnly = new RegExp('^[0-9]*$');

    if(numbersOnly.test(value) === false) {

        $(this).val(value.slice(0,-1));

    }
});

$("#newWorkoutLength").on("keyup", function(){

    // alert("OKAY")

    let value = $(this).val();

    let numbersOnly = new RegExp('^[0-9]*$');

    if(numbersOnly.test(value) === false) {

        $(this).val(value.slice(0,-1));

    }
});

$("#newWorkoutImage").on("change", function () {

    let $files = $(this).get(0).files;

    if ($files.length) {

        if ($files[0].size > $(this).data("max-size") * 1024) {

            console.log("Please select a smaller file");

            return false;
        }

        console.log("Uploading file to Imgur..");

        $("#newWorkoutImageLabel").text("Uploading image ...");

        // Set metadata (fixes localhost error 429)

        let meta = document.createElement('meta');
        meta.name = "referrer";
        meta.content = "no-referrer";
        document.getElementsByTagName('head')[0].appendChild(meta);

        //

        let apiUrl = 'https://api.imgur.com/3/image';
        let apiKey = '413853ad48c0bca';

        let settings = {
            async: true,
            crossDomain: true,
            processData: false,
            contentType: false,
            type: 'POST',
            url: apiUrl,
            headers: {
                Authorization: 'Client-ID ' + apiKey,
                Accept: 'application/json',
            },
            mimeType: 'multipart/form-data',
        };

        let formData = new FormData();

        formData.append('image', $files[0]);

        settings.data = formData;

        $.ajax(settings).done(function (response) {

            let receivedJSON = $.parseJSON(response);

            let imageUrl = receivedJSON.data.link;

            $("#newWorkoutImageUrl").val(imageUrl);
            $("#newWorkoutImageLabel").text("Image successfully uploaded");

            console.log(imageUrl);
            console.log(response);

        });
    }
});