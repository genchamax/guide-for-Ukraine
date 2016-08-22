/**
 * Created by Max on 17.08.2016.
 */
(function () {
    var regions = [];
    var places = [];
    var categories = [];
    var selectedCategories = [];

    /**
     * GET
     * Get regions data
     */
    $.ajax({
        method: "GET",
        url: "/api/regions",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            regions = data;
            fillRegionsSelect();
        }
    });

    /**
     * GET
     * Get places data
     */
    $.ajax({
        method: "GET",
        url: "/api/places",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            places = data;
        }
    });

    /**
     * GET
     * Get categories data
     */
    $.ajax({
        method: "GET",
        url: "/api/categories",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            categories = data;
            fillCategories();
        }
    });

    /**
     * Append #post-region select options with regions
     */
    function fillRegionsSelect() {
        for (var i = 0; i < regions.length; i++) {
            $("#post-region").append(
                "<option value=' " + i + " '>" + regions[i].regionName + "</option>"
            );
        }
    }

    /**
     * Append select options with cities of current region
     */
    function fillCitiesSelect() {
        var selectedRegion = getSelectedRegionIndex();
        if (selectedRegion != NaN) {
            var cities = regions[selectedRegion].cities;
            for (var i = 0; i < cities.length; i++) {
                $("#post-city").append(
                    "<option value=' " + cities[i].cityId + " '>" + cities[i].cityName + "</option>"
                );
            }
        }
    }

    /**
     * Append select options with places of current city
     */
    function fillPlacesSelect() {
        var cityId = getSelectedCityId();
        var placesOfTheCity = getPlacesOfTheCity(cityId);
        if (placesOfTheCity.length > 0) {
            for (var i = 0; i < placesOfTheCity.length; i++) {
                $("#post-place").append(
                    "<option value=' " + places[i].placeId + "'>" + places[i].placeName + "</option>"
                )
            }
        }
    }

    /**
     * Append select options with categories
     */
    function fillCategories() {
        $("#post-category").empty().append(
            "<option disabled hidden selected>Категорія</option>"
        );
        for (var i = 0; i < categories.length; i++) {
            if (selectedCategories.indexOf(categories[i]) < 0) {
                $("#post-category").append(
                    "<option value=' " + i + "'>" + categories[i].categoryName + "</option>"
                );
            }
        }
    }

    /**
     * Read value of select and cast them to Number
     * @returns {number} selected region index
     */
    function getSelectedRegionIndex() {
        return Number($("#post-region").val());
    }

    /**
     * Read value of select and cast them to Number
     * @returns {number} selected city ID
     */
    function getSelectedCityId() {
        return Number($("#post-city").val());
    }

    /**
     * Read value of select and cast them to Number
     * @returns {number} selected place id
     */
    function getSelectedPlacesId() {
        return Number($("#post-place").val());
    }

    /**
     * Return cityName from city where id == selected city id
     * @returns {*}
     */
    function getCityNameById() {
        var cities = regions[getSelectedRegionIndex()].cities;
        for (var i = 0; i < cities.length; i++ ) {
            if (cities[i].cityId === getSelectedCityId()) {
                return cities[i].cityName;
            }
        }
    }

    /**
     * Return name of selected places
     * @returns {*}
     */
    function getPlacesName() {
        var placesOfTheCity = getPlacesOfTheCity(getSelectedCityId());
        for (var i = 0; i < placesOfTheCity.length; i++) {
            if (placesOfTheCity[i].placeId === getSelectedPlacesId()) {
                return placesOfTheCity[i].placeName;
            }
        }
    }

    /**
     *
     * @param cityId selected city ID
     * @returns {Array} Array places of th city
     */
    function getPlacesOfTheCity(cityId) {
        var placesOfTheCity = [];
        for (var i = 0; i < places.length; i++) {
            if (places[i].city.cityId == cityId) {
                placesOfTheCity.push(places[i]);
            }
        }
        return placesOfTheCity;
    }

    /**
     * Read category from <select>
     * @returns {string|jQuery}
     */
    function getSelectedCategory() {
        return categories[Number ($("#post-category").val())];
    }

    //todo  Check grammar
    /**
     *  Fill cities <select> city of selected region when change value of regions <select>
     */
    $("#post-region").change(function () {
        $("#post-city").removeAttr("disabled").empty().append(
            "<option disabled selected hidden>Оберіть місто</option>"
        );
        fillCitiesSelect();
    });

    /**
     * Fill cities <select> place of selected city when change value of cities <select>
     */
    $("#post-city").change(function () {
        $("#post-place").removeAttr("disabled").empty().append(
            "<option disabled selected hidden>Оберіть місце</option>"
        );
        fillPlacesSelect();
    });

    /**
     *
     */
    $("#post-place").change(function () {

    });

    /**
     *
     */
    $("#post-category").change(function () {
        $(
            "<div class='col-md-2 col-md-offset-1 category-item'>" +
                "<span class='category-text'>" + getSelectedCategory().categoryName + "</span>" +
                "<span class='fa fa-times' onclick='deleteCategory(" + JSON.stringify(getSelectedCategory()) + ", this)'" +
            "</div>"
        ).insertAfter($("#post-category"));
        selectedCategories.push(getSelectedCategory());
        console.log(selectedCategories);
        fillCategories();
    });

    this.deleteCategory = function(category, context) {
        console.log(selectedCategories);
        console.log(selectedCategories.indexOf(category));
        selectedCategories.splice(selectedCategories.indexOf(category) - 1, 1);
        $(context).parent().remove();
        console.log(selectedCategories);
        fillCategories();
    }

})();
