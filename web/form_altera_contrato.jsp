<%-- 
    Document   : form_servico
    Created on : 26/11/2021, 19:18:33
    Author     : COUGAR
--%>

<%@page import="model.ContratoDAO"%>
<%@page import="model.Contrato"%>
<%@page import="model.ContratoServicoDAO"%>
<%@page import="model.ContratoServico"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <li class="breadcrumb-item"><a href="gerenciar_contrato.do?acao=listar">Contratos</a></li>
                        <li class="breadcrumb-item active" aria-current="page">${titulo}</li>
                    </ol>
                </nav>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">

                    <div class="card-body">
                        <h2 class="text-primary text-center mb-4">Serviços do Contrato</h2>
                        <table class="table table-bordered pb-6" id="dataTable" width="100%" cellspacing="0">
                            <thead class="text-primary">
                                <tr>
                                    <th>ID Contrato</th>
                                    <th>Serviço</th>
                                    <th>Quantidade</th>
                                    <th>Valor</th>
                                </tr>
                            </thead>
                            <jsp:useBean class="model.ContratoServicoDAO" id="csDAO" />
                            <tbody>
                                <c:forEach var="cs" items="${csDAO.getCarregaPorId(contrato.idContrato)}">
                                    <tr>
                                        <td class="text-center"> ${cs.contrato.idContrato} </td>
                                        <td> ${cs.servico.nome} </td>
                                        <td> ${cs.quantidade} </td>
                                        <td> ${cs.valor} </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <form method="post" name="form_add" action="gerenciar_contrato.do">
                            <fieldset class="mt-6 border p-2 mb-4">
                                <legend class="font-small form-control text-center text-primary"> <i class="fas fa-file-signature text-primary"></i>&nbsp;Dados Contrato </legend>
                                <div class="form-group row mb-4">
                                    <div class="col-md-3">
                                        
                                    </div>
                                    <div class="col-md-3">
                                        <label>Data Contrato</label>
                                        <input type="datetime-local" class="form-control" name="data_contrato" value="${data}" required=""> 
                                    </div>
                                    <div class="col-md-3">
                                        <label>Status</label>
                                        <select class="custom-select" name="status" required="">
                                            <option value="" disabled="">Escolha um Status</option>
                                            <option value="cancelado" <c:if test="${contrato.status == 'cancelado'}">selected=""</c:if>>Cancelado</option>
                                            <option value="aberto" <c:if test="${contrato.status == 'aberto'}">selected=""</c:if>>Em Aberto</option>
                                            <option value="finalizado" <c:if test="${contrato.status == 'finalizado'}">selected=""</c:if>>Finalizado</option>
                                        </select>
                                    </div>
                                </div>
                            </fieldset>
                                
                            <input type="hidden" name="idContrato" value="${contrato.idContrato}">
                            <input type="hidden" name="idCliente" value="${contrato.idCliente.idCliente}">
                            <input type="hidden" name="idUsuario" value="${contrato.atendente.idUsuario}">
                            <button type="submit" class="btn btn-primary btn-sm float-right ml-2" <c:if test="${usuarioLogado.idPerfil.nome == 'Atendente' && contrato.status == 'finalizado'}">disabled=""</c:if>><i class="fas fa-save"></i>&nbsp;Gravar</button>
                            <a title="Voltar" href="gerenciar_contrato.do?acao=listar" class="btn btn-success btn-sm float-right"><i class="fas fa-arrow-left"></i>&nbsp;Voltar</a>
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



