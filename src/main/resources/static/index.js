angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/shop/api/v1';



    $scope.loadStudents = function (page = 1) {
        $http({
            url: contextPath + '/students',
            method: 'GET',
            params: {
                part_name: $scope.filter ? $scope.filter.part_name : null,
                min_score: $scope.filter ? $scope.filter.min_score : null,
                max_score: $scope.filter ? $scope.filter.max_score : null
            }
        }).then(function (response) {
            $scope.StudentList = response.data.content;
            // $scope.student.min = null;
            // $scope.student.max = null;
        });
    }

    $scope.deleteStudent = function (studentId) {
        $http.delete(contextPath + '/students/' + studentId)
            .then(function (response) {
                $scope.loadStudents();
            })
    }

    $scope.changeScore = function (studentId, delta) {
        $http({
            url: contextPath + '/students/change_score',
            method: 'GET',
            params: {
                studentId: studentId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadStudents();
        });
    }

    $scope.createStudentJSON = function () {
        console.log($scope.newStudentJSON);
        $http.post(contextPath + '/students', $scope.newStudentJSON) // зашили в боди
            .then(function (response) {
                $scope.loadStudents();
                $scope.newStudentJSON = null;
            });
    }





    $scope.loadStudents();
});