/**
 * Created by Max on 13.08.2016.
 */
(function () {
    "use strict";
    var regions = [];

    $.ajax({
        type: "GET",
        url: "/api/regions/",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            regions = data;
            fillNavbar();
        }
    });

    function fillNavbar() {
        for (var i = 0; i < regions.length; i++) {
            if (regions[i].cities.length > 0) {
                $("ul.sidebar-nav").append(
                    "<li>" +
                      "<a href='#'>" + regions[i].regionName +
                         "<span class='fa fa-chevron-right'></span>" +
                        "</a>" +
                        addCitiesToRegion(i) +
                    "</li>");
            }
        }
    }

    function addCitiesToRegion(regionIndex) {
        var citiesOfTheRegion = regions[regionIndex].cities;
        var result = "<ul class='navbar-city-list'>";

        for (var i = 0; i < citiesOfTheRegion.length; i++) {
            result = result.concat(
                "<li>" +
                    "<a href='#'>" +
                        citiesOfTheRegion[i].cityName +
                        "<span class='fa fa-chevron-right'></span>" +
                    "</a>" +
                "</li>");
        }
        return result.concat("</ul>");
    }

    $("#menu-toggle").on("click", function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    // TODO ! on(event, adding by JS element, function)
    $("ul.sidebar-nav").on("click", "span", function (e) {
        e.preventDefault();
        $(this).toggleClass("fa-chevron-down");
        $(this).parent().siblings().toggleClass("show");
    });

    $("ul.sidebar-nav").on("click", ".navbar-city-list", function (e) {

    })

})();