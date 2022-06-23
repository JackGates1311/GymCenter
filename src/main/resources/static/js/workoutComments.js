$(document).ready(function() {

    let url = new URL(window.location.href);

    let id = url.searchParams.get("id");

    $.ajax({
        url: "http://localhost:8080/api/workoutComments/" + id,
        type: 'GET',
        success: function(response) {

            if(response.length == 0) {

                $("#comments").append("<div class='inlineFlex alignCenter w-100'><img class='img-responsive' " +
                    "src='https://cdn-icons-png.flaticon.com/512/5058/5058062.png'/></div>");
            }

            if(id == -1) {

                for(let i = 0; i < response.length; i++) {

                    let workoutComment = response[i];

                    let userName = workoutComment.userName;

                    if(workoutComment.postCommentAsGuest === true) {

                        userName = "Anonymous";
                    }

                    $("#comments").append('<div class="card commentContainer">\n' +
                        '\n' +
                        '                <div class="card-header dirty-white">\n' +
                        '\n' +
                        '                    <div class="inlineFlexGapSmall w-100">\n' +
                        '\n' +
                        '                        <div class="inlineFlexGapSmall commentHeader w-60">\n' +
                        '\n' +
                        '                            <span class="material-icons">account_circle</span>\n' +
                        '                            <a id="commentAuthor">' + userName + '</a>\n' +
                        '\n' +
                        '                        </div>\n' +
                        '\n' +
                        '                        <div class="inlineFlexGapSmall w-40 alignRight">\n' +
                        '\n' +
                        '        <div class="date-text">' + workoutComment.commentDateTimePosted.slice(0,10) + '</div>\n' +
                        '\n' +
                        '                        </div>\n' +
                        '\n' +
                        '                    </div>\n' +
                        '\n' +
                        '                </div>\n' +
                        '\n' +
                        '                <div class="card-body">\n' +
                        '\n' +
                        '                    <div class="row">\n' +
                        '\n' +
                        '                        <div class="col-lg-12 com-md-12 col-sm-12 col-xs-12 mb-12">\n' +
                        '\n' +
                        '                            <div class="inlineFlex gapNormal w-100">\n' +
                        '\n' +
                        '                                <p class="card-text paddingPost">' + workoutComment.commentContent + '</p>\n' +
                        '\n' +
                        '                            </div>\n' +
                        '\n' +
                        '                        </div>\n' +
                        '\n' +
                        '                    </div>\n' +

                        '                    <div class="row">\n' +

                        '                        <div class="col-lg-12 com-md-12 col-sm-12 col-xs-12 mb-12">\n' +
                        '\n' +
                        '                            <div class="inlineFlex gapNormal alignRight pointerCursor w-100">\n' +
                        '\n' +
                        '                        <a onclick="approveComment('+ workoutComment.commentId +')">' +
                        '                           <span class="material-icons">done</span></a>\n' +

                        '                        <a onclick="declineComment('+ workoutComment.commentId +')">' +
                        '                           <span class="material-icons">block</span></a> \n' +
                        '\n' +
                        '                            </div>\n' +
                        '\n' +
                        '                        </div>\n' +
                        '\n' +
                        '                    </div>\n' +
                        '\n' +
                        '                </div>\n' +
                        '\n' +
                        '            </div>\n' +
                        '\n' +
                        '        </div>\n' +
                        '\n' +
                        '    </div>');

                }

            } else {

                for(let i = 0; i < response.length; i++) {

                    let workoutComment = response[i];

                    let userName = workoutComment.userName;

                    if(workoutComment.postCommentAsGuest === true) {

                        userName = "Anonymous";
                    }

                    $("#comments").append('<div class="card commentContainer">\n' +
                        '\n' +
                        '                <div class="card-header dirty-white">\n' +
                        '\n' +
                        '                    <div class="inlineFlexGapSmall w-100">\n' +
                        '\n' +
                        '                        <div class="inlineFlexGapSmall commentHeader w-60">\n' +
                        '\n' +
                        '                            <span class="material-icons">account_circle</span>\n' +
                        '                            <a id="commentAuthor">' + userName + '</a>\n' +
                        '\n' +
                        '                        </div>\n' +
                        '\n' +
                        '                        <div class="inlineFlexGapSmall w-40 alignRight">\n' +
                        '\n' +
                        '        <div class="date-text">' + workoutComment.commentDateTimePosted.slice(0,10) + '</div>\n' +
                        '\n' +
                        '                        </div>\n' +
                        '\n' +
                        '                    </div>\n' +
                        '\n' +
                        '                </div>\n' +
                        '\n' +
                        '                <div class="card-body">\n' +
                        '\n' +
                        '                    <div class="row">\n' +
                        '\n' +
                        '                        <div class="col-lg-12 com-md-12 col-sm-12 col-xs-12 mb-12">\n' +
                        '\n' +
                        '                            <div class="inlineFlex gapNormal w-100">\n' +
                        '\n' +
                        '                                <p class="card-text paddingPost">' + workoutComment.commentContent + '</p>\n' +
                        '\n' +
                        '                            </div>\n' +
                        '\n' +
                        '                        </div>\n' +
                        '\n' +
                        '                    </div>\n' +
                        '\n' +
                        '                </div>\n' +
                        '\n' +
                        '            </div>\n' +
                        '\n' +
                        '        </div>\n' +
                        '\n' +
                        '    </div>');

                }

            }

        }
    });
});

function approveComment(id) {

    $.ajax({

        type: "POST",
        url: "http://localhost:8080/api/approveComment/" + id,
        success: function (result) {

            window.location=document.referrer

        }

    });
}

function declineComment(id) {

    $.ajax({

        type: "POST",
        url: "http://localhost:8080/api/declineComment/" + id,
        success: function (result) {

            window.location=document.referrer

        }

    });
}

