function getAllCurrencies(){
    // item from HTML class
    var dropDownFrom = document.getElementById("dropdown-from");
    // item from HTML class
    var dropDownTo = document.getElementById("dropdown-to");

    var xmlRequest = new XMLHttpRequest();

    console.log("Getting all currencies")
    xmlRequest.onload = function(){
        console.log("received response " + this.responseText);
        var jsonParsed = JSON.parse(this.responseText);

        fillCurrencies(dropDownFrom, jsonParsed);
        fillCurrencies(dropDownTo, jsonParsed);
        fillButtonInfo();
    };
    // from backend CurrencyController find all currencies who is is database
    xmlRequest.open("GET", "/currencies", true);
    xmlRequest.send();
}
    // fill full list with currencies with for loop
function fillCurrencies(dropdown, jsonParsed){
    for (var i = 0; i < jsonParsed.length; i++){
        var a = document.createElement("a");
    //dropdown-item from HTML
        a.setAttribute("class", "dropdown-item");
        var text = document.createTextNode(jsonParsed[i].currency);
        a.appendChild(text);
        a.setAttribute("value", jsonParsed[i].currency);
        dropdown.appendChild(a);
    }

}
function filterFunction(input, dropdown){
    console.log("filtering with input " + input + " on dropdown " + dropdown);
    if(input == null){
        return;
    }
    var input, filter, ul, li, a, i;
    input = document.getElementById(input);
    filter = input.value;
    console.log("received value " + filter)
    div = document.getElementById(dropdown);
    a = div.getElementsByTagName("a");
    for(i = 0; i < a.length; i++){
        if(a[i].innerHTML.indexOf(filter) >= 1){
            a[i].style.display = "";
        } else {
            a[i].style.display = "none";
        }
    }
}
    // select curency from list of currency and add it to convertation function.
function fillButtonInfo(){
    var listAs = document.querySelectorAll(".dropdown-menu a");

    for(var i = 0; i < listAs.length; i++){
        var a = listAs[i];
        a.addEventListener("click", function(evt){
            var a = evt.target;
            var button = a.parentElement.previousElementSibling;
            button.innerHTML = a.getAttribute("value");
        })
    }
}
    // AJAX method.
$("#convertButton").click(function(){
    //get from
    // Ill tried Ajax (pick button from HTML)
    var from = $("#dropdownFromButton").text();
    //get to
    // Ajax (pick get to value of currency HTML)
    var to = $("#dropdownToButton").text();
    //get value
     // Ajax (pick value of currency from HTML)
    var value = $("#valueInput").val();


    $.ajax({
        type: "POST",
        // from backend CurrencyController, convert currency math
        url: "/currencies/convert",
        data: JSON.stringify({
            "from": from,
            "to": to,
            "value": value
        }),
        contentType: "application/json",
        success: function(data){
            console.log(data);
            $("#convertResult").val(data);
        },
        error: function(data){
        // error console pop up (if u add letter not a digits)
            console.log(data);
        }
    });
});