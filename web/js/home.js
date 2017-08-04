var app = angular.module('chocoApp', []);

app.controller('ChocoController', function ($scope, $http) {
    
    $scope.user = {};
    $scope.consultation = {};
    $scope.provider = {};
    $scope.member = {};
    $scope.service = {};
    
    angular.element(document).ready(function () {
        
        $('.masthead').visibility({
            once: false,
            onBottomPassed: function() {
                $('.fixed.menu').transition('fade in');
            },
            onBottomPassedReverse: function() {
                $('.fixed.menu').transition('fade out');
            }
        });
        
        $('.ui.sidebar').sidebar('attach events', '.toc.item');
        
        $('.ui.dropdown').dropdown();
        
        $('.special.cards .image').dimmer({on: 'hover'});
        
        $('#myContainer').transition('pulse');

    });

    $('#search_provider').search({
        apiSettings: {
            url: 'UserController?word={query}&type=provider'
        },
        fields: {
            results : 'providers',
            title   : 'name',
            url     : 'html_url'
        },
        minCharacters : 1,
        onSelect: function(provider, response) {
            $scope.provider = provider;
        }
    });
    
    $('#search_member').search({
        apiSettings: {
            url: 'UserController?word={query}&type=member'
        },
        fields: {
            results : 'members',
            title   : 'name',
            url     : 'html_url'
        },
        minCharacters : 1,
        onSelect: function(member, response) {
            $scope.member = member;
        }
    });
    
    $('#search_service').search({
        apiSettings: {
            url: 'UserController?word={query}&type=service'
        },
        fields: {
            results : 'services',
            title   : 'name',
            url     : 'html_url'
        },
        minCharacters : 1,
        onSelect: function(service, response) {
            $scope.service = service;
        }
    });

    $scope.saveUser = function() {
        
        $scope.user.type = "member";
        
        $.ajax({
            type: 'POST',
            url: 'UserController',
            data: {
                action : "save",
                user : JSON.stringify($scope.user)
            },
            success: function (result) {
                
                if (result === "true") {
                    
                    $scope.user = {};
                    
                    $scope.$apply();
                    
                    swal(
                        'Correcto!',
                        'Usuario registrado!',
                        'success'
                    );
                    
                    setTimeout(function(){ window.location.reload(); }, 700);
                    
                } else {
                    
                    swal(
                        'Error!',
                        'No se ha podido insertar el usuario!',
                        'error'
                    );
                    
                }
                
            }

        });
        
    };
    
    $scope.saveConsultation = function() {
        
        var consultation = {};
        
        angular.copy($scope.consultation, consultation);
        
        consultation.provider = $scope.provider.id;
        consultation.member = $scope.member.id;
        consultation.service = $scope.service.id;
        
        $.ajax({
            type: 'POST',
            url: 'ConsultationController',
            data: {
                action : "save",
                consultation : JSON.stringify(consultation)
            },
            success: function (result) {
                
                if (result === "true") {
                    
                    $scope.consultation = {};
                    
                    $scope.$apply();
                    
                    swal(
                        'Correcto!',
                        'Consulta creada!',
                        'success'
                    );
                    
//                    setTimeout(function(){ window.location.reload(); }, 700);
                    
                } else {
                    
                    swal(
                        'Error!',
                        'No se ha podido crear la consulta!',
                        'error'
                    );
                    
                }
                
            }

        });
        
    };

    $scope.openAddUserMl = function() {
        $('#addUserMl').modal('show');
    };
    
    $scope.openConsultationMl = function() {
        $('#addConsultationMl').modal('show');
    };
    
    $scope.openAddConsultationMl = function() {
        $('#addConsultationMl').modal('show');
    };

});