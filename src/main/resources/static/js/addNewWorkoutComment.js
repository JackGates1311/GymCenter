$('#btnPost').on("click", function(e){

    let workoutCommentContentValue = $("#workoutCommentContent").val();
    let workoutRateValue = $("#workoutRate").val();
    let userCommentTypeValue = $("#userCommentType").val();

    let postCommentAsGuestValue;

    if (workoutCommentContentValue === "") {
        alert("Workout comment can not be empty!");

        e.preventDefault();

    } else {

        let url = new URL(window.location.href);

        let id = url.searchParams.get("id");

        let postCommentAsGuestValue;

        if (userCommentTypeValue === "0") {

            postCommentAsGuestValue = false;

        } else {

            postCommentAsGuestValue = true;
        }

        let workoutComment = {

            workoutId: id,
            commentContent: workoutCommentContentValue,
            workoutRate: workoutRateValue,
            postCommentAsGuest: postCommentAsGuestValue

        };

        $.ajax({

            type: "POST",
            url: "http://localhost:8080/api/workoutComments/" + id,
            data: workoutComment,
            success: function (result) {

                window.location=document.referrer

            }


        });


    }

});