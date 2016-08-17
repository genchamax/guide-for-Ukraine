/**
 * Created by Max on 15.08.2016.
 */
(function () {
    /**
     * Info about categories
     * @type {Array}
     */
    var categories = [];

    /**
     * Need for onclick() events working correct
     */
    var context = this;

    /**
     * Check if you can create/edit category
     * @type {boolean}
     */
    var isClickable = true;

    /**
     * GET
     * Receive categories data
     */
    $.ajax({
        method: "GET",
        url: "/api/categories",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            categories = data;
            createCategoryList();
        }
    });

    /**
     * DELETE
     * Delete category by category id.
     * @param categoryId
     */
    context.deleteById = function (categoryId) {
        $.ajax({
            method: "DELETE",
            url: "/api/categories/" + categoryId,
            dataType: "json",
            contentType: "application/json"
        }).always(function () {
            window.location.replace("/admin/categories");
        });
    };

    /**
     * POST
     * Create new category. Id is generated value
     * @param newCategory
     */
    function createNewCategory(newCategory) {
        $.ajax({
            type: "POST",
            url: "/api/categories",
            data: JSON.stringify(newCategory),
            dataType: "json",
            contentType: "application/json"
        }).always(function () {
            window.location.replace("/admin/categories");
        });
    };

    /**
     * PUT
     * Edit category by id. Category name get from input element.
     * @param categoryId
     */
    this.editCategoryById = function (categoryId) {

        var categoryName = $(".edit-category-input").val();

        $.ajax({
            method: "PUT",
            url: "/api/categories/" + categoryId,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                'categoryId' : categoryId,
                'categoryName' : categoryName
            })
        }).always(function () {
            window.location.replace("/admin/categories");
        });
    };

    /**
     * Create list of categories.
     */
    function createCategoryList() {
        $("#category-wrapper").append("<div class='container'><div class='row'>");
        for (var i = 0; i < categories.length; i++) {
            $("#category-wrapper .container .row").append(
                "<div class='col-md-3 category-item col-md-offset-1 col-sm-5 col-sm-offset-1 col-xs-11 col-xs-offset-1'>" +
                    "<span class='category-name'>" + categories[i].categoryName + "</span>" +
                    "<span class='fa fa-times category-delete'  onclick='deleteById(" + categories[i].categoryId + ")'></span>" +
                    "<span class='fa fa-edit category-edit' onclick='editCategory(" + JSON.stringify(categories[i]) + ", this)'></span>" +
                "</div>"
            )
        }

        $("#category-wrapper .container .row").append(
            "<div class='col-md-3 col-md-offset-1 col-sm-5 col-sm-offset-1 col-xs-11 col-xs-offset-1 add-btn'>" +
                "<span class='fa fa-plus'></span>" +
            "</div>"
        );

        $("#category-wrapper").append("</div></div>")
    }

    /**
     * Onclick handler for .add-btn. Insert input field before current element and set focus on it
     */
    $("#category-wrapper").on("click", ".add-btn", function () {
        if (isClickable) {
            $(
                "<div class='col-md-3 category-item col-md-offset-1 col-sm-5 col-sm-offset-1'>" +
                   "<input type='text' placeholder='Введіть назву категорії'/>" +
                    "<span class='fa fa-times cancel'></span><span class='fa fa-arrow-right accept'></span>" +
                "</div>"
            ).insertBefore(".add-btn");
            $("#category-wrapper input[type='text']").focus();
        }
        isClickable = false;
    });

    /**
     * Work if isClickable === true;
     * Create input field before current elem with value of category name. Hide current element
     * Set isClickable = false
     * @param category
     * @param context current context (this)
     */
    this.editCategory = function (category, context) {
        if (isClickable) {
            $(
                "<div class='col-md-3 category-item col-md-offset-1 col-sm-5 col-sm-offset-1'>" +
                    "<input type='text' placeholder='Введіть назву категорії' " +
                        "class='edit-category-input' value='" + category.categoryName + "' />" +
                    "<span class='fa fa-times cancel-edit-category'></span>" +
                    "<span class='fa fa-arrow-right accept-edit-category' " +
                        "onclick=\"editCategoryById(" + category.categoryId + ") \"></span>" +
                "</div>"
            ).insertBefore($(context).parent());
            $("#category-wrapper .edit-category-input").focus();
            $(context).parent().toggleClass("hidden");
        }
        isClickable = false;
    };

    /**
     * Onclick listener for .cancel-edit-category.
     * Remove input field and make category item visible
     * Set isClickable true
     */
    $("#category-wrapper").on("click", ".cancel-edit-category", function () {
       $(this).parent().remove();
        $(".category-item").removeClass("hidden");
        isClickable = true;
    });

    /**
     * Onclick listener for .cancel.
     * Delete input field for new category.
     * Set isClickable true
     */
    $("#category-wrapper").on("click", ".cancel", function (e) {
        $(this).parent().remove();
        isClickable = true;
    });

    /**
     * Onclick listener for .accept.
     * Read from <input> text (category name) and pass AJAX query with them.
     */
    $("#category-wrapper").on("click", ".accept", function () {
        createNewCategory({
            'categoryName': $("#category-wrapper input[type='text']").val().toString()
        });
    })
})();