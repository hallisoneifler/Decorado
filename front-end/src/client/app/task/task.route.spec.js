/* jshint -W117, -W030 */
describe('task routes', function() {
  describe('state', function() {
    var view = 'app/task/task.html';

    beforeEach(function() {
      module('app.task', bard.fakeToastr);
      bard.inject('$httpBackend', '$location', '$rootScope', '$state', '$templateCache');
    });

    beforeEach(function() {
      $templateCache.put(view, '');
    });

    it('should map state task to url /task ', function() {
      expect($state.href('task', {})).to.equal('/task');
    });

    it('should map task route to task View template', function() {
      expect($state.get('task').templateUrl).to.equal(view);
    });

    it('of task should work with $state.go', function() {
      $state.go('task');
      $rootScope.$apply();
      expect($state.is('task'));
    });
  });
});
