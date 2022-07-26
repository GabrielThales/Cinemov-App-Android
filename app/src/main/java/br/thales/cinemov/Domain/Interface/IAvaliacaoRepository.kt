package br.thales.cinemov.Domain.Interface

import br.thales.cinemov.Domain.Entities.Avaliacao

interface IAvaliacaoRepository : IRepository<Avaliacao> {

    fun getMinhasAvaliacoes(uuid : String)

}