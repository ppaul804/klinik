<%-- 
    Document   : listarNada
    Created on : 16/10/2021, 03:59:06
    Author     : Danilo
--%>
<jsp:include page="header.jsp"/>
<div class="row">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
            <li class="breadcrumb-item text-sm"><a class="opacity-5 text-dark" href="index.jsp">Index</a></li>
            <li class="breadcrumb-item text-sm text-dark active" aria-current="page">Listar Nada</li>
        </ol>
        <h6 class="font-weight-bolder mb-0 pb-2 ">Listar Nada</h6>
    </nav>
    <div class="col-12">
        <div class="card  mb-4">
            <div class="card-header pb-0">
                <a href="gerenciar_listarnada.do?acao=cadastrar" class="btn btn-success btn-sm px-3" style="float: right">Cadastrar Nada</a>
            </div>
            <div class="card-body px-0 pt-0 pb-2">
                <div class="table-responsive p-0">
                    <table class="table align-items-center mb-0" id="listar">
                        <thead>
                            <tr>
                                <th class="text-uppercase text-secondary font-weight-bolder">Usuários</th>
                                <th class="text-uppercase text-secondary font-weight-bolder ps-2">Função</th>
                                <th class="text-center text-uppercase text-secondary font-weight-bolder">Status</th>
                                <th class="text-center text-uppercase text-secondary font-weight-bolder">Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div class="d-flex px-2 py-1">
                                        <div>
                                            <img src="assets/img/team-2.jpg" class="avatar avatar-sm me-3" alt="user1">
                                        </div>
                                        <div class="d-flex flex-column justify-content-center">
                                            <h6 class="mb-0 text-sm">Danilo Lourenço</h6>
                                            <p class="text-xs text-secondary mb-0">danilo@gmail.com</p>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <p class="text-xs font-weight-bold mb-0">Atendende</p>
                                    <!--<p class="text-xs text-secondary mb-0">Organization</p>-->
                                </td>
                                <td class="align-middle text-center text-sm">
                                    <span class="badge badge-sm bg-gradient-success">Ativo</span>
                                </td>
                                <td class="align-middle text-center">
                                    <a class="btn btn-info text-white px-3 mb-0" href="#"><i class="fas fa-pencil-alt text-white me-2"></i>Edit</a>
                                    <a class="btn btn-danger text-white px-3 mb-0" href="#"><i class="far fa-trash-alt me-2"></i>Delete</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="d-flex px-2 py-1">
                                        <div>
                                            <img src="assets/img/team-3.jpg" class="avatar avatar-sm me-3" alt="user2">
                                        </div>
                                        <div class="d-flex flex-column justify-content-center">
                                            <h6 class="mb-0 text-sm">Alexa Liras</h6>
                                            <p class="text-xs text-secondary mb-0">alexa@creative-tim.com</p>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <p class="text-xs font-weight-bold mb-0">Programator</p>
                                    <p class="text-xs text-secondary mb-0">Developer</p>
                                </td>
                                <td class="align-middle text-center text-sm">
                                    <span class="badge badge-sm bg-gradient-secondary">Inativo</span>
                                </td>
                                <td class="align-middle text-center">
                                    <a class="btn text-dark px-3 mb-0" href="#"><i class="fas fa-pencil-alt text-dark me-2"></i>Edit</a>
                                    <a class="btn text-danger px-3 mb-0" href="#"><i class="far fa-trash-alt me-2"></i>Delete</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
