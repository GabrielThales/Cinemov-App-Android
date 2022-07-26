package br.thales.cinemov.Infra.Interfaces

import br.thales.cinemov.Application.Interfaces.FirebaseEventListener
import br.thales.cinemov.Domain.Entities.Avaliacao

interface AvaliacaoEventListener : FirebaseEventListener<Avaliacao> {

}