angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/shop';

    $scope.student = {};


    $scope.loadStudents = function () {
        console.log($scope.student.min);
        $http({
            url: contextPath + '/students',
            method: 'GET',
            params: {
                min: $scope.student.min,
                max: $scope.student.max
            }
        }).then(function (response) {
            $scope.StudentList = response.data;
            // $scope.student.min = null;
            // $scope.student.max = null;
        });
    }

    $scope.deleteStudent = function (studentId) {
        $http.get(contextPath + '/students/delete/' + studentId)
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

    $scope.sumTwoNumbers = function () {
        $http({
            url: contextPath + '/calc/add',
            method: 'GET',
            params: {
                a: $scope.calcAdd.a,
                b: $scope.calcAdd.b
            }
        }).then(function (response) {
            alert('Сумма равна ' + response.data.value);
            $scope.calcAdd = null;
        });
    }



    $scope.loadStudents();
});