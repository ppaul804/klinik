<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>KLINIK | ${titulo}</title>

        <!-- Custom fonts for this template-->
        <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="assets/css/sb-admin-2.min.css" rel="stylesheet">

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <jsp:include page="menu.jsp"/>
            
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">
                    
                    <jsp:include page="topbar.jsp"/>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="gerenciar_index.do?acao=index">Home</a></li>
                        <li class="breadcrumb-item"><a href="gerenciar_menu.do?acao=listar">Menu</a></li>
                        <li class="breadcrumb-item active" aria-current="page">${titulo}</li>
                    </ol>
                </nav>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">

                    <div class="card-body">
                        <form method="post" name="form_add" action="gerenciar_menu.do">

                            <fieldset class="mt-5 border p-2 mb-4">
                                <legend class="font-small form-control text-center text-primary"> <i class="fas fa-user-tie text-primary"></i>&nbsp;Dados Menu </legend>

                                <div class="form-group row mb-4">
                                    <div class="col-md-6">
                                        <label>Nome Menu</label>
                                        <input type="text" class="form-control" name="nome" placeholder="Nome do Menu" value="${menu.nome}" required=""> 
                                    </div>
                                    <div class="col-md-6">
                                        <label>Link</label>
                                        <input type="text" class="form-control" name="link" placeholder="Link" value="${menu.link}" required=""> 
                                    </div>
                                    <div class="col-md-6">
                                        <label>Ícone</label>
                                        <input type="text" class="form-control" name="icone" placeholder="Ícone" value="${menu.icone}" required=""> 
                                    </div>
                                    <div class="col-md-6">
                                        <label>Exibir</label>
                                        <select class="custom-select" name="exibir"  required="">
                                            <option value="${menu.exibir}" disabled="" selected="">Escolha...</option>
                                            <option value="1" <c:if test="${menu.exibir == 1}">selected=""</c:if> >Sim</option>
                                            <option value="0" <c:if test="${menu.exibir == 0}">selected=""</c:if> >Não</option>
                                        </select>
                                    </div>
                                </div>
                            </fieldset>

                            <input type="hidden" name="idMenu" value="${menu.idMenu}">
                            <button type="submit" class="btn btn-primary btn-sm float-right ml-2"><i class="fas fa-save"></i>&nbsp;Gravar</button>
                            <a title="Voltar" href="gerenciar_menu.do?acao=listar" class="btn btn-success btn-sm float-right"><i class="fas fa-arrow-left"></i>&nbsp;Voltar</a>
                        </form>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <jsp:include page="footer.jsp"/>

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="assets/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="assets/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="assets/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="assets/js/demo/chart-area-demo.js"></script>
<script src="assets/js/demo/chart-pie-demo.js"></script>

</body>

</html>

