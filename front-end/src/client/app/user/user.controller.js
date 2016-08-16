(function() {
  'use strict';

  angular
    .module('app.core')
    .controller('UserController', UserController);

  UserController.$inject = ['logger', 'userService'];

  /* @ngInject */
  function UserController(logger, userService) {
    var vm = this;
    vm.title = 'User';
    vm.users = [];

    vm.currentUser = window.localStorage.getItem('token');
    vm.isAdmin = window.localStorage.getItem('admin');
    vm.edit = false;
    activate();

    function activate() {
      getUsers();
      logger.info('Activated User View');
    }

    function getUsers() {
      return userService.getUsers().then(function(data) {
        vm.users = data;
        return vm.users;
      });
    }

    vm.saveUser = function saveUser() {
      userService.save(vm.user);
      vm.user = {};
      getUsers();
      vm.edit = false;
    };

    vm.removeUser = function removeUser() {
      userService.remove(vm.user);
    };

    vm.cancel = function cancel() {
      vm.user = {};
      vm.edit = false;
    };
  }
})();
