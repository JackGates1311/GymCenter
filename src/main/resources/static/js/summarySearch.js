$('#summaryDateStart').datepicker({
    uiLibrary: 'bootstrap4',
    format: 'yyyy-mm-dd',
    modal: true,
    footer: true
});

$('#summaryDateEnd').datepicker({
    uiLibrary: 'bootstrap4',
    format: 'yyyy-mm-dd',
    modal: true,
    footer: true
});

$('#btnSummarySearch').on("click", function(e){

    let summaryDateStartValue = $("#summaryDateStart").val();
    let summaryDateEndValue = $("#summaryDateEnd").val();

    if(summaryDateStartValue > summaryDateEndValue)
    {
        alert("Please select a valid date range");

        e.preventDefault();
    }

});