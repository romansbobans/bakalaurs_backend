var categoryApp = angular.module("category-app", []);

function MainPageController($scope) {
    $scope.actionData = "";
    $scope.newCategories = [{lang: "", name: ""}];

    $scope.categoryData = JSON.parse('[{"thumb_url":"public/images/categories/notifications_email_on0.png","id":"Muzeji","object_description":[{"lang":"LV","name":"Muzeji"}, {"lang":"ENG",  "name":"Museums"}]}]');

//**********Start add Category functions**********

    $scope.openAddCategory = function() {
        $("#add-category-window, .lock").toggle();
    }

    $scope.closeAddCategory = function() {
        $("#add-category-window, .lock").toggle();
        $scope.newCategories = [{lang: "", name: ""}];
    }

    $scope.getAddButtonType = function(item, length) {
        if ((length == 1) || ($scope.newCategories.indexOf(item) == (length - 1))) {
            return false;
        } else {
            return true;
        }
    }

    $scope.addNewField = function() {
        $scope.newCategories.push({lang: "", name: ""});
    }

    $scope.removeField = function(item) {
        var index = $scope.newCategories.indexOf(item);
        $scope.newCategories.splice(index, 1);
    }

//**********End add Category functions**********

//**********Start change picture functions**********

    $scope.openImageWindow = function(item) {
        $scope.actionData = item;

        $("#change-image-window, .lock").toggle();
    }

    $scope.closeImageWindow = function() {
        $scope.actionData = {};

        $("#change-image-window, .lock").toggle();
    }

//**********End change picture functions**********

//**********Start edit Category functions**********
    $scope.openEditWindow = function(item) {
        $scope.actionData = item;

        $("#edit-category-window, .lock").toggle();
    }

    $scope.closeEditWindow = function() {
        $scope.actionData = {};

        $("#edit-category-window, .lock").toggle();
    }

//**********End edit Category functions**********

//**********Start delete Category functions**********

    $scope.openDeleteCategory = function(item) {
        $scope.actionData = item;

        $("#delete-category-window, .lock").toggle();
    }

    $scope.closeDeleteCategory = function() {
        $scope.actionData = {};

        $("#delete-category-window, .lock").toggle();
    }

    $scope.deleteCategory = function() {
        $scope.closeDeleteCategory();
    }

//**********End delete Category functions**********
}


$().ready(function(){
    $("#options a").click(function(e) {
        e.preventDefault();
    });
});

