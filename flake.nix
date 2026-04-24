{
  description = "Ambiente de desenvolvimento em Java";
  inputs.nixpkgs.url = "github:nixos/nixpkgs/nixpkgs-unstable";

  outputs = { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in {
      devShells.${system}.default = pkgs.mkShell {
        packages = with pkgs; [
          jdk21
          maven
          gradle
          jdt-language-server
        ];

        shellHook = ''
          export _JAVA_AWT_WM_NONREPARENTING=1
          echo "☕ Java dev environment loaded!"
          echo "-> $(java -version 2>&1 | head -1)"
          alias j="mvn exec:java -Dexec.mainClass="organ.project.Main""
        '';
      };
    };
}
