// /* jshint -W117, -W030 */
// describe('TaskController', function() {
//   var controller;
//
//   beforeEach(function() {
//     bard.appModule('app.task');
//     bard.inject('$controller', '$log', '$rootScope');
//   });
//
//   beforeEach(function() {
//     controller = $controller('TaskController');
//     $rootScope.$apply();
//   });
//
//   bard.verifyNoOutstandingHttpRequests();
//
//   describe('Task controller', function() {
//     it('should be created successfully', function() {
//       expect(controller).to.be.defined;
//     });
//
//     describe('after activate', function() {
//       it('should have title of Task', function() {
//         expect(controller.title).to.equal('Task');
//       });
//
//       it('should have logged "Activated"', function() {
//         expect($log.info.logs).to.match(/Activated/);
//       });
//     });
//   });
// });
