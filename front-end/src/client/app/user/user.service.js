(function() {
  'use strict';

  angular
    .module('app.core')
    .service('userService', userService);

  userService.$inject = ['Restangular', 'exception', 'logger'];
  /* @ngInject */
  function userService(Restangular, exception, logger) {
    var User = Restangular.all('users');

    var service = {
      getUsers:getUsers,
      save:saveUser,
      remove:removeUser
    };
    return service;

    function saveUser(user) {
      return User.post(user)
      .then(success)
      .catch(fail);

      function success(response) {
        return response.data;
      }
      function fail(e) {
        return exception.catcher('Failed for ...')(e);
      }
    }

    function getUsers() {
      return User.getList()
        .then(success)
        .catch(fail);

      function success(response) {
        return response.plain();
      }

      function fail(e) {
        return exception.catcher('Failed for ...')(e);
      }
    }

    function removeUser(user) {
      User.remove(user)
      .then(success)
      .catch(fail);

      function success(response) {
        return response.data;
      }

      function fail(e) {
        return exception.catcher('Failed for ...')(e);
      }
    }
  }
})();
