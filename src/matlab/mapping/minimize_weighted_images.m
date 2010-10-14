function [optimum_value, chain_map, coefficients] = minimize_weighted_images(candidate_map, homotopies, W)

    homotopies_dimension = size(homotopies, 1);
    codomain_dimension = size(candidate_map, 1);
    domain_dimension = size(candidate_map, 2);

    cvx_begin
        variable c(homotopies_dimension);
        variable P(codomain_dimension, domain_dimension);

        minimize weighted_objective(P, W);
        subject to
            P == compute_chain_map(c, candidate_map, homotopies);
            c <= 1
            c >= -1
            %P >= 0
            %P <= 1
    cvx_end

    optimum_value = cvx_optval;
    chain_map = P;
    coefficients = c;