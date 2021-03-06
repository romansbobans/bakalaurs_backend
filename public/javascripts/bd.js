/**
 * Created by Juris on 17.05.2014..
 */
var viewsApp = angular.module("views-app", []);

function MainPageController($scope) {
    $scope.action = "";
    $scope.actionData = "";

    $scope.viewsData = [
        {id: "1", title: "Test1", description: "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"},
        {id: "2", title: "Test2", description: "........................................................."},
        {id: "3", title: "Test3", description: "........................................................."},
        {id: "4", title: "Test4", description: "........................................................."}];

    $scope.openEditWindow = function(id) {
        $scope.action = "edit-view";
        $scope.actionData = id;

        $("#edit-window").fadeToggle();
        $(".lock").fadeToggle();
    }

    $scope.closeEditWindow = function() {
        $scope.action = "";
        $scope.actionData = "";

        $("#edit-window").fadeToggle();
        $(".lock").fadeToggle();
    }

    $scope.openDeleteWindow = function(id) {
        $scope.action = "delete-view";
        $scope.actionData = id;

        $("#delete-view-window").fadeToggle();
        $(".lock").fadeToggle();
    }

    $scope.closeDeleteViewWindow = function() {
        $scope.action = "";
        $scope.actionData = "";

        $("#delete-view-window").fadeToggle();
        $(".lock").fadeToggle();
    }
}


$().ready(function(){
    $("#options a").click(function(e) {
        e.preventDefault();
    });
});

