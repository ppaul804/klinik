<%-- 
    Author     : GREGORI
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

        <!-- Custom styles for this template -->
        <link href="assets/css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="assets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>

    <body>


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
                                <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                                <li class="breadcrumb-item"><a href="listar_menu.jsp">Listar Menu</a></li>
                                <li class="breadcrumb-item"><a href="listar_menu.jsp">Cadastrar Menu</a></li>
                                <li class="breadcrumb-item active" aria-current="page">${titulo}</li>
                            </ol>
                        </nav>

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">

                            <div class="card-body">
                                <form action="gerenciar_menu.do" method="POST" name="form_add" >

                                    <fieldset class="mt-5 border p-2 mb-4">

                                        <div class="form-group row mb-4">
                                            <div class="col-md-5">
                                                <label>Nome</label>
                                                <input type="text" class="form-control" name="nome" placeholder="Nome do menu" value="${menu.nome}"> 
                                            </div>
                                            <div class="col-md-4">
                                                <label>Link</label>
                                                <input type="text" class="form-control" name="link" placeholder="Link do menu" value="${menu.link}">
                                            </div>
                                            <div class="col-md-3">
                                                <label>Icone</label>
                                                <input type="text" class="form-control" name="icone" value="${menu.icone}">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-md-3">
                                                <label>Exibir</label>
                                                <select class="custom-select" name="exibir">
                                                    <option value="0"  selected="">Escolha uma opçao</option>
                                                    <option value="1" <c:if test="${menu.exibir==1}" >selected=""</c:if>> Yes </option>>
                                                    <option value="2" <c:if test="${menu.exibir==2}" >selected=""</c:if>> Not </option>>
                                                    </select>
                                                </div>
                                            </div>
                                            </div>
                                        </fieldset>

                                        <div>
                                            <input type="hidden" name="idMenu" value="${menu.idMenu}">
                                        <button type="submit" class="btn btn-primary btn-sm float-right m-2"><i class="fas fa-save"></i>&nbsp;Salvar</button>
                                        <a title="Voltar" href="listar_menu.jsp" class="btn btn-success btn-sm float-right m-2"><i class="fas fa-arrow-left"></i>&nbsp;Voltar</a>
                                    </div
                                </form>
                            </div>
                        </div>
                        <jsp:include page="footer.jsp"/>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Content Wrapper -->


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
