var app = angular.module('chocoApp', []);

app.controller('ChocoController', function ($scope, $http) {
    
    $scope.user = {};
    $scope.consultation = {};
    $scope.provider = {};
    $scope.member = {};
    $scope.service = {};
    
    $scope.members = [];
    $scope.providers = [];
    $scope.consultations = [];
    
    $scope.user.type = "";
    
    angular.element(document).ready(function () {
        initElements();
        initValidator();
        
        $.ajax({
            type: 'POST',
            url: 'UserController',
            data: {
                action: "list",
                type: "member"
            },
            success: function (result) {
                $scope.members = JSON.parse(result);
            }

        });
        
        $.ajax({
            type: 'POST',
            url: 'UserController',
            data: {
                action: "list",
                type: "provider"
            },
            success: function (result) {
                $scope.providers = JSON.parse(result);
            }

        });
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
        
        if ($scope.user.type === "provider")
            if ($scope.user.password !== $scope.user.rePassword){
                swal(
                    'Error!',
                    'Las contraseñas no coinciden!',
                    'error'
                );
                return;
            }
        
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
                    
                    setTimeout(function(){ window.location.reload(); }, 1000);
                    
                } else {
                    
                    console.log(result);
                    
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
                    
                    $scope.closeAddConsultationMl();
                    
                    swal(
                        'Correcto!',
                        'Consulta creada!',
                        'success'
                    );
                    
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
    
    $scope.openAddConsultationMl = function() {
        $('#addConsultationMl').modal('show');
    };
    
    $scope.closeAddConsultationMl = function() {
        $('#addConsultationMl').modal('hide');
    };
    
    $scope.openListUsersMl = function() {
        $('#listUsersMl').modal('show');
    };
    
    $scope.openListConsultationsMl = function() {
        $('#listConsultationsMl').modal('show');
    };
    
    function initElements() {
        
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
        
    }
    
    function initValidator() {
        
        $('#addUserForm').form({
            fields: {
                type: {
                    identifier  : 'type',
                    rules: [
                        {
                            type   : 'empty',
                            prompt : 'Por favor, especifica un tipo'
                        }
                    ]
                }
            }
        });
        
    }
    
});