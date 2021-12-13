<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>KLINIK | ${titulo}</title>

        <!-- Custom fonts for this template-->
        <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" 
              rel="stylesheet">

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

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Página Inicial</h1>
                        </div>

                        <!-- Content Row -->
                        <div class="row">

                            <!-- Clientes -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <a href="gerenciar_cliente.do?acao=listar">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-lg font-weight-bold text-primary text-uppercase ml-5">Clientes</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa fa-child fa-3x text-primary"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>

                            <!-- Consultas -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <a href="gerenciar_consulta.do?acao=listar">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-lg font-weight-bold text-success text-uppercase ml-5">Consultas</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-stethoscope fa-3x text-success"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>

                            <!-- Contratos -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-info shadow h-100 py-2">
                                    <a href="gerenciar_contrato.do?acao=listar">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-lg font-weight-bold text-info text-uppercase ml-5">Contratos</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-file-signature fa-3x text-info"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>

                            <!-- Serviços -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-warning shadow h-100 py-2">
                                    <a href="gerenciar_servico.do?acao=listar">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                        <div class="text-lg font-weight-bold text-warning text-uppercase ml-5">Serviços</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-briefcase fa-3x text-warning"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>

                        <!-- Content Row -->

                        <div class="row">

                            <div class="col-lg-6 mb-4">

                                <!-- Illustrations -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Consultas de Em Aberto</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="text-center">
                                            <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 15rem;" src="assets/img/consultas.svg" alt="">
                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-striped table-borderless">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Nome Cliente</th>
                                                        <th class="text-center">Data Consulta</th>
                                                    </tr>
                                                </thead>
                                                <jsp:useBean class="model.ConsultaDAO" id="ctDAO"/>
                                                <tbody>
                                                    <c:forEach var="consulta" items="${ctDAO.consultasEmAberto()}">
                                                        <tr>
                                                            <td>${consulta.idConsulta}</td>
                                                            <td>${consulta.cliente.nome}</td>
                                                            <td class="text-center">
                                                                <fmt:formatDate value="${consulta.data_consulta}" pattern="dd/MM/yyyy 'às' HH:mm" />
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 mb-4">

                                <!-- Illustrations -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Últimos clientes cadastrados</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="text-center">
                                            <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 14rem;" src="assets/img/clientes.svg" alt="">
                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-striped table-borderless">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Nome</th>
                                                        <th class="text-center">CPF</th>
                                                    </tr>
                                                </thead>
                                                <jsp:useBean class="model.ClienteDAO" id="cDAO"/>
                                                <tbody>
                                                    <c:forEach var="cliente" items="${cDAO.ultimosCadastrados()}">
                                                        <tr>
                                                            <td>${cliente.idCliente}</td>
                                                            <td>${cliente.nome}</td>
                                                            <td class="text-center">${cliente.cpf}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
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

