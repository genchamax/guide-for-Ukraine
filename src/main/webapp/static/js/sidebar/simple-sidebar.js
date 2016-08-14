/**
 * Created by Max on 13.08.2016.
 */
(function () {
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
            $("ul.sidebar-nav").append("<li><a href='#'>" + regions[i].regionName + "</a></li>");
        }
    }


    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
})();