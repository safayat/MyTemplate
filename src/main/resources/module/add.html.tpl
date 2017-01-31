<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-files-o"></i> Customer #ca_module_name# Add/Update</h3>
            </div>
        </div>
        <!-- Form validations -->
        <div class="row">
            <div class="col-lg-12">
                <form name="clientForm" class="form-validate form-horizontal" id="feedback_form" method="get" ng-submit="update#ca_module_name#()">
                    <section class="panel">
                        <header class="panel-heading">
                            Login
                        </header>
                        <div class="panel-body">
                            <div class="form">
                                #formData#
                                <span ng-show="successMessage" style="color: green;margin-left: 40%">{{successMessage}}</span>
                                <span ng-show="errorMsg" style="color: red;margin-left: 40%">{{errorMsg}}</span>

                            </div>

                        </div>
                    </section>
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <button  class="btn btn-primary" type="submit">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</section>


