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

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="gerenciar_index.do?acao=index">Home</a></li>
                        <li class="breadcrumb-item"><a href="gerenciar_usuarios.do?acao=listar">Usuários</a></li>
                        <li class="breadcrumb-item active" aria-current="page">${titulo}</li>
                    </ol>
                </nav>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">

                    <div class="card-body">
                        <form method="post" name="form_add">

                            <fieldset class="mt-5 border p-2 mb-4">
                                <legend class="font-small form-control text-center text-primary"> <i class="fas fa-user-tie text-primary"></i>&nbsp;Dados Pessoais </legend>

                                <div class="form-group row mb-4">
                                    <div class="col-md-5">
                                        <label>Nome</label>
                                        <input type="text" class="form-control" name="nome" placeholder="Seu nome" value="${usuario.nome}"> 
                                    </div>
                                    <div class="col-md-4">
                                        <label>Email</label>
                                        <input type="email" class="form-control" name="email" placeholder="Seu email" value="${usuario.email}">
                                    </div>
                                    <div class="col-md-3">
                                        <label>Data de Nascimento</label>
                                        <input type="date" class="form-control" name="dataDeNascimento" value="${usuario.dataDeNascimento}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-3">
                                        <label>CPF</label>
                                        <input type="text" class="form-control" name="cpf" placeholder="Seu CPF" value="${usuario.cpf}"> 
                                    </div>
                                    <div class="col-md-3">
                                        <label>RG</label>
                                        <input type="text" class="form-control" name="rg" placeholder="Seu RG" value="${usuario.rg}">
                                    </div>
                                    <div class="col-md-3">
                                        <label>Telefone</label>
                                        <input type="text" class="form-control" name="telefone" placeholder="Seu telefone" value="${usuario.telefone}">
                                    </div>
                                    <div class="col-md-3">
                                        <label>Sexo</label>
                                        <select class="custom-select" name="sexo">
                                            <option value="0" <c:if test="${usuario.sexo == 0}">selected=""</c:if> >Feminino</option>
                                            <option value="1" <c:if test="${usuario.sexo == 1}">selected=""</c:if> >Masculino</option>
                                            <option value="2" <c:if test="${usuario.sexo == 2}">selected=""</c:if> >Outros</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row mb-4">
                                    <div class="col-md-5">
                                        <label>Endereço</label>
                                        <input type="text" class="form-control" name="endereco" placeholder="Seu endereço" value="${usuario.endereco}"> 
                                    </div>
                                    <div class="col-md-4">
                                        <label>Complemento</label>
                                        <input type="email" class="form-control" name="complemento" placeholder="Seu complemento" value="${usuario.complemento}">
                                    </div>
                                    <div class="col-md-3">
                                        <label>Cidade</label>
                                        <input type="text" class="form-control" name="cidade" placeholder="Sua cidade" value="${usuario.cidade}">
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="mt-5 border p-2 mb-4">
                                <legend class="font-small form-control text-center text-primary"> <i class="fas fa-tools text-primary"></i>&nbsp;Configurações </legend>
                                <div class="form-group row">
                                    <div class="col-md-3">
                                        <label>Login</label>
                                        <input type="text" class="form-control" name="login" placeholder="Seu login" value="${usuario.login}">
                                    </div>
                                    <div class="col-md-3">
                                        <label>Senha</label>
                                        <input type="password" class="form-control" name="senha" placeholder="Sua senha" value="${usuario.senha}">
                                    </div>
                                    <div class="col-md-3">
                                        <label>Status</label>
                                        <select class="custom-select" name="status">
                                            <option value="0">Inativo</option>
                                            <option value="1">Ativo</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <label>Perfil</label>
                                        <select class="custom-select" name="perfil">
                                            <option value="4">Dentista</option>
                                            <option value="3">Atendente</option>
                                            <option value="2">Gerente</option>
                                            <option value="1">Administrador</option>
                                        </select>
                                    </div>
                                </div>
                            </fieldset>

                            <input type="hidden" name="idUsuario" value="${usuario.idUsuario}">
                            <button type="submit" class="btn btn-primary btn-sm float-right ml-2"><i class="fas fa-save"></i>&nbsp;Salvar</button>
                            <a title="Voltar" href="gerenciar_usuarios.do?acao=listar" class="btn btn-success btn-sm float-right"><i class="fas fa-arrow-left"></i>&nbsp;Voltar</a>
                        </form>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; KLINIK <%= LocalDate.now().getYear()%></span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

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

