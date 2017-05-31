describe("employeeapp", function () {
    beforeEach(module("employeeapp"));
    describe('controller: employeeController', function () {
        var ctrl, Employee, $scope;
     

        beforeEach(inject(function ($rootScope, $controller) {
            $scope = $rootScope.$new();
            Employees = {
                query: function () { 
                   
                },
                save:function () {
                    $scope.result = '{"result":true}';
                },
                 update:function () {
                    $scope.result = '{"result":true}';
                },
                delete:function () {
                     $scope.result = '{"result":true}';
                }
              
            };

            spyOn(Employees, 'query').and.returnValue('[{"id":1,"empname":"aravind","designation":"dev","email":"aravind.m@kgfsl.com","phone":"8807548039","address":"coimbatore"}]'); // <----------- HERE

            ctrl = $controller('employeeController', { $scope: $scope, Employee: Employees });
        }));

        it('Should call get all()', function () {
            console.log($scope.Employees);     
            expect($scope.Employees).toBe('[{"id":1,"empname":"aravind","designation":"dev","email":"aravind.m@kgfsl.com","phone":"8807548039","address":"coimbatore"}]');
        });
        
        it('Should call create', function () {
            $scope.Employee = {"empname":"reka","designation":"Dev","email":"reka.v@kgfsl.com","phone":"577888","address":"CBE"}
            console.log($scope.result);
            $scope.saveEmployee($scope.Employee);
            expect($scope.result).toBe('{"result":true}');
        });
        
        it('Should call update', function () {
            $scope.Employee = {"id":1,"empname":"reka","designation":"Dev","email":"reka.v@kgfsl.com","phone":"577888","address":"CBE"};
            console.log($scope.result);
            $scope.updateEmployee($scope.Employee);
            expect($scope.result).toBe('{"result":true}');
        });
        
        it('Should call delete', function () {
            $scope.Employee = {"id":1};
           $scope.deleteEmployee($scope.Employee);
            expect($scope.result).toBe('{"result":true}');
        });
               
    });
});